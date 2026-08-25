package org.example.arraytask.parser.impl;

import org.example.arraytask.exception.CustomException;
import org.example.arraytask.factory.IntegerArrayWrapperFactory;
import org.example.arraytask.parser.IntegerArrayParser;
import org.example.arraytask.validator.ArrayValidator;

import java.util.Arrays;

public class IntegerArrayParserImpl implements IntegerArrayParser {
    private final ArrayValidator validator;

    public IntegerArrayParserImpl(ArrayValidator validator) {
        this.validator = validator;
    }

    @Override
    public int[] parse(String line) throws CustomException {
        if (line == null) {
            throw new CustomException("Provided line is null.");
        }
        String strippedLine = line.strip();
        String[] elements = strippedLine.split("\\s*[,; ]\\s*");
        try {
            return Arrays.stream(elements)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new CustomException("Failed to parse integers from line: " + strippedLine, e);
        }
    }
}
