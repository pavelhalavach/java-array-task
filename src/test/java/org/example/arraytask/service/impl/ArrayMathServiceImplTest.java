package org.example.arraytask.service.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Unit tests for ArrayMathServiceImpl")
class ArrayMathServiceImplTest {
    private ArrayMathServiceImpl mathService;

    @BeforeEach
    void setUp() {
        mathService = new ArrayMathServiceImpl();
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("min() - Valid array - Should return minimum value")
    @MethodSource("provideArraysForMinTest")
    void min_ValidArray_ShouldReturnMinimumValue(int[] inputArray, int expectedMin) throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{3, 1, 4, 1, 5});
        OptionalInt result = mathService.min(array);
        assertTrue(result.isPresent());
        assertEquals(1, result.getAsInt());
    }

    @Test
    @Order(2)
    @DisplayName("min() - Empty array - Should return empty OptionalInt")
    void min_EmptyArray_ShouldReturnEmptyOptionalInt() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{});
        OptionalInt result = mathService.min(array);
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(3)
    @DisplayName("min() - Null array - Should throw CustomException")
    void min_NullArray_ShouldThrowCustomException() {
        assertThrows(CustomException.class, () -> mathService.min(null));
    }

    @Test
    @Order(4)
    @DisplayName("max() - Valid array - Should return maximum value")
    void max_ValidArray_ShouldReturnMaximumValue() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{3, 1, 4, 1, 5});
        OptionalInt result = mathService.max(array);
        assertTrue(result.isPresent());
        assertEquals(5, result.getAsInt());
    }

    @Test
    @Order(5)
    @DisplayName("max() - Empty array - Should return empty OptionalInt")
    void max_EmptyArray_ShouldReturnEmptyOptionalInt() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{});
        OptionalInt result = mathService.max(array);
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(6)
    @DisplayName("max() - Null array - Should throw CustomException")
    void max_NullArray_ShouldThrowCustomException() {
        assertThrows(CustomException.class, () -> mathService.max(null));
    }

    @Test
    @Order(7)
    @DisplayName("sum() - Valid array - Should return sum of elements")
    void sum_ValidArray_ShouldReturnSumOfElements() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{3, 1, 4, 1, 5});
        OptionalLong result = mathService.sum(array);
        assertTrue(result.isPresent());
        assertEquals(14, result.getAsLong());
    }

    @Test
    @Order(8)
    @DisplayName("sum() - Empty array - Should return empty OptionalLong")
    void sum_EmptyArray_ShouldReturnEmptyOptionalLong() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{});
        OptionalLong result = mathService.sum(array);
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(9)
    @DisplayName("sum() - Null array - Should throw CustomException")
    void sum_NullArray_ShouldThrowCustomException() {
        assertThrows(CustomException.class, () -> mathService.sum(null));
    }

    @Test
    @Order(10)
    @DisplayName("average() - Valid array - Should return average of elements")
    void average_ValidArray_ShouldReturnAverageOfElements() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{3, 1, 4, 1, 5});
        OptionalDouble result = mathService.average(array);
        assertTrue(result.isPresent());
        assertEquals(2.8, result.getAsDouble(), 0.01);
    }

    @Test
    @Order(11)
    @DisplayName("average() - Empty array - Should return empty OptionalDouble")
    void average_EmptyArray_ShouldReturnEmptyOptionalDouble() throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(new int[]{});
        OptionalDouble result = mathService.average(array);
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(12)
    @DisplayName("average() - Null array - Should throw CustomException")
    void average_NullArray_ShouldThrowCustomException() {
        assertThrows(CustomException.class, () -> mathService.average(null));
    }

    private static Stream<Object[]> provideArraysForMinTest() {
        return Stream.of(
                new Object[]{new int[]{3, 1, 4, 1, 5}, 1},
                new Object[]{new int[]{10, 20, 30, 40}, 10},
                new Object[]{new int[]{-5, -10, -3}, -10}
        );
    }
}