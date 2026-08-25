package org.example.arraytask.repository.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.repository.ArraySpecification;
import org.example.arraytask.service.ArrayMathService;

import java.util.OptionalLong;

public class SumSpecification implements ArraySpecification {

    private final long expectedSum;
    private final ComparisonOperator operator;
    private final ArrayMathService mathService;
    private static final Logger logger = LogManager.getLogger();


    public SumSpecification(long expectedSum, ComparisonOperator operator, ArrayMathService mathService) {
        this.expectedSum = expectedSum;
        this.operator = operator;
        this.mathService = mathService;
    }

    @Override
    public boolean specify(IntegerArrayWrapper array) {
        try {
            OptionalLong optionalSum = mathService.sum(array);

            return optionalSum.isPresent() && switch (operator) {
                case GREATER -> optionalSum.getAsLong() > expectedSum;
                case LESS -> optionalSum.getAsLong() < expectedSum;
                case EQUAL -> optionalSum.getAsLong() == expectedSum;
            };
        } catch (CustomException e) {
            logger.log(Level.WARN, "Array with ID {} caused an exception during specification check. Treating as unsafe/invalid.", array.getId(), e);
            return false;
        }
    }
}
