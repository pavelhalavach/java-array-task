package org.example.arraytask.repository.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.repository.ArraySpecification;

public class IdSpecification implements ArraySpecification {

    private final int id;
    private final ComparisonOperator operator;

    public IdSpecification(int id, ComparisonOperator operator) {
        this.id = id;
        this.operator = operator;
    }

    @Override
    public boolean specify(IntegerArrayWrapper array) {
        return switch (operator) {
            case GREATER -> array.getId() > id;
            case LESS -> array.getId() < id;
            case EQUAL -> array.getId() == id;
        };
    }
}
