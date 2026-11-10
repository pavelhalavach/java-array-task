package org.example.arraytask.repository.specification.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.repository.specification.ArraySpecification;

public class IdSpecification implements ArraySpecification {

    private final int id;
    private final int upperId;
    private final ComparisonOperator operator;

    public IdSpecification(int id, ComparisonOperator operator) throws CustomException {
        if (operator == ComparisonOperator.BETWEEN) {
            throw new CustomException("BETWEEN requires two bounds, use IdSpecification(int, int)");
        }
        this.id = id;
        this.upperId = id;
        this.operator = operator;
    }

    public IdSpecification(int minId, int maxId) throws CustomException {
        if (minId > maxId) {
            throw new CustomException("minId must not be greater than maxId: " + minId + " > " + maxId);
        }
        this.id = minId;
        this.upperId = maxId;
        this.operator = ComparisonOperator.BETWEEN;
    }

    @Override
    public boolean specify(IntegerArrayWrapper array) {
        return switch (operator) {
            case GREATER -> array.getId() > id;
            case LESS -> array.getId() < id;
            case EQUAL -> array.getId() == id;
            case BETWEEN -> array.getId() >= id && array.getId() <= upperId;
        };
    }
}
