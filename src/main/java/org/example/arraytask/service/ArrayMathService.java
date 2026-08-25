package org.example.arraytask.service;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface ArrayMathService {
    OptionalInt min(IntegerArrayWrapper array) throws CustomException;

    OptionalInt max(IntegerArrayWrapper array) throws CustomException;

    OptionalLong sum(IntegerArrayWrapper array) throws CustomException;

    OptionalDouble average(IntegerArrayWrapper array) throws CustomException;
}
