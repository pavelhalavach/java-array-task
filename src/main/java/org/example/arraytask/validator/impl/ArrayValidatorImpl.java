package org.example.arraytask.validator.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.arraytask.validator.ArrayValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayValidatorImpl implements ArrayValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final Pattern NUMERIC_LIST_VALIDATOR_PATTERN = Pattern.compile("(\\d+[,;]?\\s*)+");

    @Override
    public boolean isLineValid(String line) {
        logger.log(Level.DEBUG,"Validating line \"{}\"", line);
        if (line == null || line.isBlank()) {
            return false;
        }
        Matcher matcher = NUMERIC_LIST_VALIDATOR_PATTERN.matcher(line);
        return matcher.matches();
    }
}
