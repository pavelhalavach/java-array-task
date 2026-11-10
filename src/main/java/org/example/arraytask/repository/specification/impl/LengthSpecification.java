package org.example.arraytask.repository.specification.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.repository.specification.ArraySpecification;

public class LengthSpecification implements ArraySpecification {

    private final int expectedLength;
    private final int upperLength;

    private final ComparisonOperator operator;

    public LengthSpecification(int expectedLength, ComparisonOperator operator) throws CustomException {
        if (operator == ComparisonOperator.BETWEEN) {
            throw new CustomException("BETWEEN requires two bounds, use LengthSpecification(int, int)");
        }
        this.expectedLength = expectedLength;
        this.upperLength = expectedLength;
        this.operator = operator;
    }

    public LengthSpecification(int minLength, int maxLength) throws CustomException {
        if (minLength > maxLength) {
            throw new CustomException("minLength must not be greater than maxLength: "
                    + minLength + " > " + maxLength);
        }
        this.expectedLength = minLength;
        this.upperLength = maxLength;
        this.operator = ComparisonOperator.BETWEEN;
    }

    @Override
    public boolean specify(IntegerArrayWrapper array) {
        int actualLength = array.length();

        return switch (operator) {
            case GREATER -> actualLength > expectedLength;
            case LESS -> actualLength < expectedLength;
            case EQUAL -> actualLength == expectedLength;
            case BETWEEN -> actualLength >= expectedLength && actualLength <= upperLength;
        };
    }
}
