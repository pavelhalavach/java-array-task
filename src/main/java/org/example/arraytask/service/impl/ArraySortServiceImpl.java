package org.example.arraytask.service.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.service.ArraySortService;

public class ArraySortServiceImpl implements ArraySortService {
    @Override
    public void bubbleSort(IntegerArrayWrapper array) throws CustomException {
        validateArray(array);

        for (int i = 0; i < array.length() - 1; i++) {
            for (int j = 0; j < array.length() - 1 - i; j++) {

                if (array.get(j) > array.get(j + 1)) {
                    int temp = array.get(j);

                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public void insertionSort(IntegerArrayWrapper array) throws CustomException {
        validateArray(array);

        for (int i = 1; i < array.length(); i++) {
            int current = array.get(i);
            int j = i - 1;

            while (j >= 0 && array.get(j) > current) {
                array.set(j + 1, array.get(j));
                j--;
            }

            array.set(j + 1, current);
        }
    }

    private void validateArray(IntegerArrayWrapper array) throws CustomException {
        if (array == null) {
            throw new CustomException("Array " + array + " must not be null");
        }
    }
}
