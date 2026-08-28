package org.example.arraytask.service.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.service.ArrayMathService;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class ArrayMathServiceImpl implements ArrayMathService {
    @Override
    public OptionalInt min(IntegerArrayWrapper array) throws CustomException {
        validateArray(array);

        if (array.length() == 0) {
            return OptionalInt.empty();
        }

        int min = array.get(0);

        for (int i = 1; i < array.length(); i++) {
            min = Math.min(min, array.get(i));
        }

        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt max(IntegerArrayWrapper array) throws CustomException {
        validateArray(array);

        if (array.length() == 0) {
            return OptionalInt.empty();
        }

        int max = array.get(0);

        for (int i = 1; i < array.length(); i++) {
            max = Math.max(max, array.get(i));
        }

        return OptionalInt.of(max);
    }

    @Override
    public OptionalLong sum(IntegerArrayWrapper array) throws CustomException {
        validateArray(array);

        if (array.length() == 0) {
            return OptionalLong.empty();
        }

        long sum = 0;

        for (int i = 0; i < array.length(); i++) {
            sum += array.get(i);
        }

        return OptionalLong.of(sum);
    }

    @Override
    public OptionalDouble average(IntegerArrayWrapper array) throws CustomException {
        validateArray(array);

        if (array.length() == 0) {
            return OptionalDouble.empty();
        }

        long sum = 0;

        for (int i = 0; i < array.length(); i++) {
            sum += array.get(i);
        }

        return OptionalDouble.of((double) sum / array.length());
    }

    private void validateArray(IntegerArrayWrapper array) throws CustomException {
        if (array == null) {
            throw new CustomException("Array " + array + " must not be null");
        }
    }
}
