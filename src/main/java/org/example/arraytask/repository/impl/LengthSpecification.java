package org.example.arraytask.repository.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.repository.ArraySpecification;

public class LengthSpecification implements ArraySpecification {

    private final int expectedLength;
    private final ComparisonOperator operator;

    public LengthSpecification(int expectedLength, ComparisonOperator operator) {
        this.expectedLength = expectedLength;
        this.operator = operator;
    }

    @Override
    public boolean specify(IntegerArrayWrapper array) {
        int actualLength = array.length();

        return switch (operator) {
            case GREATER -> actualLength > expectedLength;
            case LESS -> actualLength < expectedLength;
            case EQUAL -> actualLength == expectedLength;
        };
    }
}
