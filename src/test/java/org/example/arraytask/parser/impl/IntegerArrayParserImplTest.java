package org.example.arraytask.parser.impl;

import org.example.arraytask.exception.CustomException;
import org.example.arraytask.validator.impl.ArrayValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit tests for IntegerArrayParserImpl")
class IntegerArrayParserImplTest {

    private IntegerArrayParserImpl parser;

    @BeforeEach
    void setUp() {
        parser = new IntegerArrayParserImpl(new ArrayValidatorImpl());
    }

    @Test
    @DisplayName("parse() - Valid line - Should return integer array")
    void parse_ValidLine_ShouldReturnIntegerArray() throws CustomException {
        // Arrange
        String line = "1, 2, 3, 4";

        // Act
        int[] result = parser.parse(line);

        // Assert
        assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(4, result.length, "Array length should be 4"),
                () -> assertEquals(1, result[0], "First element should be 1"),
                () -> assertEquals(2, result[1], "Second element should be 2"),
                () -> assertEquals(3, result[2], "Third element should be 3"),
                () -> assertEquals(4, result[3], "Fourth element should be 4")
        );
    }

    @Test
    @DisplayName("parse() - Empty line - Should return empty array")
    void parse_EmptyLine_ShouldReturnEmptyArray() throws CustomException {
        // Arrange
        String line = "";

        // Act
        int[] result = parser.parse(line);

        // Assert
        assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(0, result.length, "Array length should be 0")
        );
    }

    @Test
    @DisplayName("parse() - Null line - Should throw CustomException")
    void parse_NullLine_ShouldThrowCustomException() {
        // Arrange
        String line = null;

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> parser.parse(line));
        assertAll(
                () -> assertNotNull(exception, "Exception should not be null"),
                () -> assertTrue(exception.getMessage().contains("Provided line is null"), "Exception message should contain 'Provided line is null'")
        );
    }

    @Test
    @DisplayName("parse() - Invalid line - Should throw CustomException")
    void parse_InvalidLine_ShouldThrowCustomException() {
        // Arrange
        String line = "1, 2, abc, 4";

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> parser.parse(line));
        assertAll(
                () -> assertNotNull(exception, "Exception should not be null"),
                () -> assertTrue(exception.getMessage().contains("Failed to parse integers"), "Exception message should contain 'Failed to parse integers'")
        );
    }

    @Test
    @DisplayName("parse() - Idempotence - Multiple calls should return same result")
    void parse_Idempotence_MultipleCalls_ShouldReturnSameResult() throws CustomException {
        // Arrange
        String line = "5, 6, 7, 8";

        // Act
        int[] firstCall = parser.parse(line);
        int[] secondCall = parser.parse(line);

        // Assert
        assertAll(
                () -> assertNotNull(firstCall, "First call result should not be null"),
                () -> assertNotNull(secondCall, "Second call result should not be null"),
                () -> assertArrayEquals(firstCall, secondCall, "Results of multiple calls should be identical")
        );
    }
}