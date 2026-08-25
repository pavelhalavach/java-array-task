package org.example.arraytask.observer;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;

public interface ArrayObserver {
    void actionPerformed(IntegerArrayWrapper arrayWrapper) throws CustomException;
}
