package org.example.arraytask.factory;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;

public interface IntegerArrayWrapperFactory {
    IntegerArrayWrapper create(int[] array) throws CustomException;
}
