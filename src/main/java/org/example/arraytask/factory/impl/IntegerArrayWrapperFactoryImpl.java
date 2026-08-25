package org.example.arraytask.factory.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.factory.IntegerArrayWrapperFactory;

public class IntegerArrayWrapperFactoryImpl implements IntegerArrayWrapperFactory {
    @Override
    public IntegerArrayWrapper create(int[] array) throws CustomException {
        return new IntegerArrayWrapper(array);
    }
}
