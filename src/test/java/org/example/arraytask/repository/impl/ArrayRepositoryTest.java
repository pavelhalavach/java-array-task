package org.example.arraytask.repository.impl;

import org.example.arraytask.creator.IntegerArrayWrapperCreator;
import org.example.arraytask.creator.impl.IntegerArrayWrapperCreatorImpl;
import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.factory.IntegerArrayWrapperFactory;
import org.example.arraytask.factory.impl.IntegerArrayWrapperFactoryImpl;
import org.example.arraytask.observer.ArrayObserver;
import org.example.arraytask.observer.impl.ArrayObserverImpl;
import org.example.arraytask.repository.specification.ArraySpecification;
import org.example.arraytask.repository.specification.impl.ComparisonOperator;
import org.example.arraytask.repository.specification.impl.IdSpecification;
import org.example.arraytask.repository.specification.impl.LengthSpecification;
import org.example.arraytask.repository.specification.impl.SumSpecification;
import org.example.arraytask.service.impl.ArrayMathServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit tests for ArrayRepository")
class ArrayRepositoryTest {

    private ArrayRepository repository;
    private IntegerArrayWrapperCreator creator;

    @BeforeEach
    void setUp() {
        IntegerArrayWrapperFactory arrayFactory = new IntegerArrayWrapperFactoryImpl();
        ArrayObserver observer = new ArrayObserverImpl();
        repository = ArrayRepository.getInstance();
        repository.removeAll(repository.getIntegerArrayWrapperList());
        creator = new IntegerArrayWrapperCreatorImpl(arrayFactory, repository, observer);
    }

    @Test
    @DisplayName("add() - Valid object - Should add object to repository")
    void add_ValidObject_ShouldAddObjectToRepository() throws CustomException {
        IntegerArrayWrapper wrapper = creator.createAndRegister(new int[]{1, 2, 3});

        // Act
        List<IntegerArrayWrapper> result = repository.getIntegerArrayWrapperList();

        // Assert
        assertAll(
                () -> assertNotNull(result, "Repository list should not be null"),
                () -> assertEquals(1, result.size(), "Repository size should be 1"),
                () -> assertEquals(wrapper, result.get(0), "Added object should match")
        );
    }

    @Test
    @DisplayName("remove() - Existing object - Should remove object from repository")
    void remove_ExistingObject_ShouldRemoveObjectFromRepository() throws CustomException {
        // Arrange
        IntegerArrayWrapper wrapper = creator.createAndRegister(new int[]{1, 2, 3});

        // Act
        repository.remove(wrapper);
        List<IntegerArrayWrapper> result = repository.getIntegerArrayWrapperList();

        // Assert
        assertTrue(result.isEmpty(), "Repository should be empty after removal");
    }

    @Test
    @DisplayName("query() - IdSpecification - Should return objects with matching ID")
    void query_IdSpecification_ShouldReturnMatchingObjects() throws CustomException {
        // Arrange
        IntegerArrayWrapper wrapper1 = creator.createAndRegister(new int[]{1, 2, 3});
        IntegerArrayWrapper wrapper2 = creator.createAndRegister(new int[]{4, 5, 6});

        ArraySpecification specification = new IdSpecification(wrapper1.getId(), ComparisonOperator.EQUAL);

        // Act
        List<IntegerArrayWrapper> result = repository.query(specification);

        // Assert
        assertAll(
                () -> assertNotNull(result, "Result list should not be null"),
                () -> assertEquals(1, result.size(), "Result size should be 1"),
                () -> assertEquals(wrapper1, result.get(0), "Returned object should match specification"),
                () -> assertNotEquals(wrapper2, result.get(0), "Returned object should match specification")
        );
    }

    @Test
    @DisplayName("query() - NameSpecification - Should return objects with matching name")
    void query_LengthSpecification_ShouldReturnMatchingObjects() throws CustomException {
        // Arrange
        IntegerArrayWrapper wrapper1 = creator.createAndRegister(new int[]{1, 2, 3});
        IntegerArrayWrapper wrapper2 = creator.createAndRegister(new int[]{4, 5, 6});

        ArraySpecification specification = new LengthSpecification(3, ComparisonOperator.EQUAL);

        // Act
        List<IntegerArrayWrapper> result = repository.query(specification);

        // Assert
        assertAll(
                () -> assertNotNull(result, "Result list should not be null"),
                () -> assertEquals(2, result.size(), "Result size should be 1"),
                () -> assertEquals(wrapper1, result.get(0), "Returned object should match specification"),
                () -> assertEquals(wrapper2, result.get(1), "Returned object should match specification")
        );
    }

    @Test
    @DisplayName("query() - SumSpecification - Should return objects with sum greater than threshold")
    void query_SumSpecification_ShouldReturnMatchingObjects() throws CustomException {
        // Arrange
        IntegerArrayWrapper wrapper1 = creator.createAndRegister(new int[]{1, 2, 3});
        IntegerArrayWrapper wrapper2 = creator.createAndRegister(new int[]{4, 5, 6});

        ArraySpecification specification = new SumSpecification(10, ComparisonOperator.GREATER, new ArrayMathServiceImpl());

        // Act
        List<IntegerArrayWrapper> result = repository.query(specification);

        // Assert
        assertAll(
                () -> assertNotNull(result, "Result list should not be null"),
                () -> assertEquals(1, result.size(), "Result size should be 1"),
                () -> assertEquals(wrapper2, result.get(0), "Returned object should match specification")
        );
    }

    @Test
    @DisplayName("getInstance() - Singleton behavior - Should return same instance")
    void getInstance_SingletonBehavior_ShouldReturnSameInstance() {
        // Act
        ArrayRepository instance1 = ArrayRepository.getInstance();
        ArrayRepository instance2 = ArrayRepository.getInstance();

        // Assert
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    @DisplayName("remove() - Non-existent object - Should not throw exception")
    void remove_NonExistentObject_ShouldNotThrowException() throws CustomException {
        // Arrange
        IntegerArrayWrapper wrapper = creator.createAndRegister(new int[]{1, 2, 3});

        // Act & Assert
        assertDoesNotThrow(() -> repository.remove(wrapper), "Removing non-existent object should not throw exception");
    }
}