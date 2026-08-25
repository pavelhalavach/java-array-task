package org.example.arraytask.repository;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;

public interface ArraySpecification {
    boolean specify(IntegerArrayWrapper integerArrayWrapper) throws CustomException;
}
