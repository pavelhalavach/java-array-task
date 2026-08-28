package org.example.arraytask.service.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Unit tests for ArraySortServiceImpl")
class ArraySortServiceImplTest {
    private ArraySortServiceImpl sortService;

    @BeforeEach
    void setUp() {
        sortService = new ArraySortServiceImpl();
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("bubbleSort() - Valid array - Should sort array in ascending order")
    @MethodSource("provideArraysForAscendingSortTest")
    void bubbleSort_ValidArray_ShouldSortArray(int[] inputArray, int[] expectedArray) throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(inputArray);
        sortService.bubbleSort(array);
        assertArrayEquals(expectedArray, array.toArray());
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("insertionSort() - Valid array - Should sort array in ascending order")
    @MethodSource("provideArraysForAscendingSortTest")
    void insertionSort_ValidArray_ShouldSortArray(int[] inputArray, int[] expectedArray) throws CustomException {
        IntegerArrayWrapper array = new IntegerArrayWrapper(inputArray);
        sortService.insertionSort(array);
        assertArrayEquals(expectedArray, array.toArray());
    }

    @Test
    @Order(3)
    @DisplayName("bubbleSort() - Null array - Should throw CustomException")
    void bubbleSort_NullArray_ShouldThrowCustomException() {
        assertThrows(CustomException.class, () -> sortService.bubbleSort(null));
    }

    @Test
    @Order(4)
    @DisplayName("insertionSort() - Null array - Should throw CustomException")
    void insertionSort_NullArray_ShouldThrowCustomException() {
        assertThrows(CustomException.class, () -> sortService.insertionSort(null));
    }

    private static Stream<Object[]> provideArraysForAscendingSortTest() {
        return Stream.of(
                new Object[]{new int[]{3, 1, 4, 1, 5}, new int[]{1, 1, 3, 4, 5}},
                new Object[]{new int[]{10, 20, 30, 40}, new int[]{10, 20, 30, 40}},
                new Object[]{new int[]{-5, -10, -3}, new int[]{-10, -5, -3}}
        );
    }
}