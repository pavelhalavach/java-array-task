package org.example.arraytask.validator.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit tests for ArrayValidatorImpl")
class ArrayValidatorImplTest {

    private ArrayValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new ArrayValidatorImpl();
    }

    @Test
    @DisplayName("isLineValid() - Valid numeric line - Should return true")
    void isLineValid_ValidNumericLine_ShouldReturnTrue() {
        String validLine = "1, 2, 3, 4, 5";
        assertTrue(validator.isLineValid(validLine));
    }

    @Test
    @DisplayName("isLineValid() - Valid numeric line with semicolons - Should return true")
    void isLineValid_ValidNumericLineWithSemicolons_ShouldReturnTrue() {
        String validLine = "1; 2; 3; 4; 5";
        assertTrue(validator.isLineValid(validLine));
    }

    @Test
    @DisplayName("isLineValid() - Invalid line with letters - Should return false")
    void isLineValid_InvalidLineWithLetters_ShouldReturnFalse() {
        String invalidLine = "1, 2, a, 4, 5";
        assertFalse(validator.isLineValid(invalidLine));
    }

    @Test
    @DisplayName("isLineValid() - Empty line - Should return false")
    void isLineValid_EmptyLine_ShouldReturnFalse() {
        String emptyLine = "";
        assertFalse(validator.isLineValid(emptyLine));
    }

    @Test
    @DisplayName("isLineValid() - Null line - Should return false")
    void isLineValid_NullLine_ShouldReturnFalse() {
        assertFalse(validator.isLineValid(null));
    }

    @Test
    @DisplayName("isLineValid() - Line with extra spaces - Should return true")
    void isLineValid_LineWithExtraSpaces_ShouldReturnTrue() {
        String validLine = " 1 , 2 , 3 , 4 , 5 ";
        assertTrue(validator.isLineValid(validLine));
    }
}