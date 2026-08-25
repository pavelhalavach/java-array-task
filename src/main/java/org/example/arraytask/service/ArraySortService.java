package org.example.arraytask.service;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;

public interface ArraySortService {
    void bubbleSort(IntegerArrayWrapper array) throws CustomException;

    void insertionSort(IntegerArrayWrapper array) throws CustomException;
}
