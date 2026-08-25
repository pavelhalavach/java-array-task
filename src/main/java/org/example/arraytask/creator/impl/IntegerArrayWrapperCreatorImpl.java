package org.example.arraytask.creator.impl;

import org.example.arraytask.creator.IntegerArrayWrapperCreator;
import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.factory.IntegerArrayWrapperFactory;
import org.example.arraytask.observer.ArrayObserver;
import org.example.arraytask.repository.ArrayRepository;

public class IntegerArrayWrapperCreatorImpl implements IntegerArrayWrapperCreator {
    private final IntegerArrayWrapperFactory wrapperFactory;
    private final ArrayRepository arrayRepository;
    private final ArrayObserver arrayObserver;

    public IntegerArrayWrapperCreatorImpl(IntegerArrayWrapperFactory wrapperFactory, ArrayRepository arrayRepository, ArrayObserver arrayObserver) {
        this.wrapperFactory = wrapperFactory;
        this.arrayRepository = arrayRepository;
        this.arrayObserver = arrayObserver;
    }

    @Override
    public IntegerArrayWrapper createAndRegister(int[] array) throws CustomException {
        IntegerArrayWrapper wrapper = wrapperFactory.create(array);

        wrapper.attachObserver(arrayObserver);
        arrayRepository.add(wrapper);
        wrapper.notifyObservers();

        return wrapper;
    }
}
