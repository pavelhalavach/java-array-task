package org.example.arraytask.parser;

import org.example.arraytask.exception.CustomException;

import java.util.List;

public interface IntegerArrayParser {
    int[] parse(String line) throws CustomException;
}
