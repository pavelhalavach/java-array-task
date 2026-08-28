package org.example.arraytask.reader.impl;

import org.example.arraytask.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit tests for ArrayReaderImpl")
class ArrayReaderImplTest {
    private ArrayReaderImpl reader;

    @BeforeEach
    void setUp() {
        reader = new ArrayReaderImpl();
    }

    @Test
    @DisplayName("readLinesFromFile() - Valid file - Should return lines")
    void readLinesFromFile_ValidFile_ShouldReturnLines() throws Exception {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, List.of("line1", "line2", "line3"));

        List<String> lines = reader.readLinesFromFile(tempFile.toString());

        assertAll(
                () -> assertNotNull(lines, "Lines should not be null"),
                () -> assertEquals(3, lines.size(), "Lines size should be 3"),
                () -> assertEquals("line1", lines.get(0), "First line should match"),
                () -> assertEquals("line2", lines.get(1), "Second line should match"),
                () -> assertEquals("line3", lines.get(2), "Third line should match")
        );

        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("readLinesFromFile() - Empty file - Should return empty list")
    void readLinesFromFile_EmptyFile_ShouldReturnEmptyList() throws Exception {
        Path tempFile = Files.createTempFile("test", ".txt");

        List<String> lines = reader.readLinesFromFile(tempFile.toString());

        assertAll(
                () -> assertNotNull(lines, "Lines should not be null"),
                () -> assertTrue(lines.isEmpty(), "Lines should be empty")
        );

        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("readLinesFromFile() - Invalid file path - Should throw CustomException")
    void readLinesFromFile_InvalidFilePath_ShouldThrowCustomException() {
        String invalidPath = "invalid:/path/to/file.txt";

        CustomException exception = assertThrows(CustomException.class, () -> reader.readLinesFromFile(invalidPath));
        assertAll(
                () -> assertNotNull(exception, "Exception should not be null"),
                () -> assertTrue(exception.getMessage().contains("invalid"), "Exception message should contain 'invalid'")
        );
    }

    @Test
    @DisplayName("readLinesFromFile() - Non-existent file - Should throw CustomException")
    void readLinesFromFile_NonExistentFile_ShouldThrowCustomException() {
        String nonExistentFile = "nonexistentfile.txt";

        CustomException exception = assertThrows(CustomException.class, () -> reader.readLinesFromFile(nonExistentFile));
        assertAll(
                () -> assertNotNull(exception, "Exception should not be null"),
                () -> assertTrue(exception.getMessage().contains("Failed to read file"), "Exception message should contain 'Failed to read file'")
        );
    }

    @Test
    @DisplayName("readLinesFromFile() - Null file path - Should throw CustomException")
    void readLinesFromFile_NullFilePath_ShouldThrowCustomException() {
        CustomException exception = assertThrows(CustomException.class, () -> reader.readLinesFromFile(null));
        assertAll(
                () -> assertNotNull(exception, "Exception should not be null"),
                () -> assertTrue(exception.getMessage().contains("Provided file name is null"), "Exception message should contain 'Provided file name is null'")
        );
    }

    @Test
    @DisplayName("readLinesFromFile() - Idempotence - Multiple calls should return same result")
    void readLinesFromFile_Idempotence_MultipleCallsShouldReturnSameResult() throws Exception {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, List.of("line1", "line2", "line3"));

        List<String> firstCall = reader.readLinesFromFile(tempFile.toString());
        List<String> secondCall = reader.readLinesFromFile(tempFile.toString());

        assertAll(
                () -> assertNotNull(firstCall, "First call result should not be null"),
                () -> assertNotNull(secondCall, "Second call result should not be null"),
                () -> assertEquals(firstCall, secondCall, "Results of multiple calls should be identical")
        );

        Files.deleteIfExists(tempFile);
    }
}