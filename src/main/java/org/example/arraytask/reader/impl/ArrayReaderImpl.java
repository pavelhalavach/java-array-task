package org.example.arraytask.reader.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.reader.ArrayReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class ArrayReaderImpl implements ArrayReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<String> readLinesFromFile(String fileName) throws CustomException {
        if (fileName == null) {
            throw new CustomException("Provided file name is null.");
        }

        try {
            logger.log(Level.DEBUG, "Reading file '{}'.", fileName);
            Path filePath = Path.of(fileName);
            return Files.readAllLines(filePath);
        } catch (InvalidPathException e) {
            throw new CustomException("Provided file name '" + fileName + "' is invalid.", e);
        } catch (IOException e) {
            throw new CustomException("Failed to read file '" + fileName + "'.", e);
        }
    }
}
