package org.example.arraytask.observer;

import org.example.arraytask.exception.CustomException;

public interface Observable {
    void attachObserver(ArrayObserver observer) throws CustomException;
    void detachObserver();
    void notifyObservers() throws CustomException;
}
