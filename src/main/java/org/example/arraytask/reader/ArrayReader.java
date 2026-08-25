package org.example.arraytask.reader;

import org.example.arraytask.exception.CustomException;

import java.util.List;

public interface ArrayReader {
    List<String> readLinesFromFile(String fileName) throws CustomException;
}
