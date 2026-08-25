package org.example.arraytask.repository.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.repository.ArraySpecification;
import org.example.arraytask.service.ArrayMathService;

import java.util.OptionalLong;

public class SumSpecification implements ArraySpecification {

    private final long expectedSum;
    private final ComparisonOperator operator;
    private final ArrayMathService mathService;

    public SumSpecification(long expectedSum, ComparisonOperator operator, ArrayMathService mathService) {
        this.expectedSum = expectedSum;
        this.operator = operator;
        this.mathService = mathService;
    }

    @Override
    public boolean specify(IntegerArrayWrapper array) throws CustomException {
        long actualSum = mathService.sum(array).orElseThrow(() ->
                new CustomException("Cannot calculate sum: array is empty."));

        return switch (operator) {
            case GREATER -> actualSum > expectedSum;
            case LESS -> actualSum < expectedSum;
            case EQUAL -> actualSum == expectedSum;
        };
    }
}
