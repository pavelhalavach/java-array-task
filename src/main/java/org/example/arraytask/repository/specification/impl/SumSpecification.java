package org.example.arraytask.repository.specification.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.repository.specification.ArraySpecification;
import org.example.arraytask.service.ArrayMathService;

import java.util.OptionalLong;

public class SumSpecification implements ArraySpecification {

    private final long expectedSum;
    private final long upperSum;
    private final ComparisonOperator operator;
    private final ArrayMathService mathService;
    private static final Logger logger = LogManager.getLogger();


    public SumSpecification(long expectedSum, ComparisonOperator operator, ArrayMathService mathService) throws CustomException {
        if (operator == ComparisonOperator.BETWEEN) {
            throw new CustomException(
                    "BETWEEN requires two bounds, use SumSpecification(long, long, ArrayMathService)");
        }
        this.expectedSum = expectedSum;
        this.upperSum = expectedSum;
        this.operator = operator;
        this.mathService = mathService;
    }

    public SumSpecification(long minSum, long maxSum, ArrayMathService mathService) {
        if (minSum > maxSum) {
            throw new IllegalArgumentException("minSum must not be greater than maxSum: " + minSum + " > " + maxSum);
        }
        this.expectedSum = minSum;
        this.upperSum = maxSum;
        this.operator = ComparisonOperator.BETWEEN;
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
                case BETWEEN -> optionalSum.getAsLong() >= expectedSum && optionalSum.getAsLong() <= upperSum;
            };
        } catch (CustomException e) {
            logger.log(Level.WARN, "Array with ID {} caused an exception during specification check. Treating as unsafe/invalid.", array.getId(), e);
            return false;
        }
    }
}
