package org.example.arraytask.creator;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;

public interface IntegerArrayWrapperCreator {
    IntegerArrayWrapper createAndRegister(int[] array) throws CustomException;
}
