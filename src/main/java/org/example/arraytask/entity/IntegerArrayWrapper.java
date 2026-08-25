package org.example.arraytask.entity;

import org.example.arraytask.observer.ArrayObserver;
import org.example.arraytask.observer.Observable;
import org.example.arraytask.util.IdUtil;
import org.example.arraytask.exception.CustomException;

import java.util.Arrays;

public class IntegerArrayWrapper implements Observable {
    private int id;
    private int[] array;
    private ArrayObserver observer;

    public IntegerArrayWrapper(int[] array) throws CustomException {
        if (array == null) {
            throw new CustomException("Array cannot be null");
        }
        this.id = IdUtil.incrementId();
        this.array = array.clone();
    }

    public void set(int index, int value) throws CustomException {
        if (index < 0 || index >= array.length) {
            throw new CustomException("Given index [" + index + "] is out of bounds of the array.");
        }
        array[index] = value;
        notifyObservers();
    }

    public int get(int index) throws CustomException {
        if (index < 0 || index >= array.length) {
            throw new CustomException("Given index [" + index + "] is out of bounds of the array.");
        }
        return array[index];
    }

    public int getId() {
        return id;
    }

    public int length() {
        return array.length;
    }
    public int[] toArray() {
        return array.clone();
    }

    @Override
    public void attachObserver(ArrayObserver observer) throws CustomException {
        if (observer == null) {
            throw new CustomException("Cannot attach observer: provided argument is null.");
        }
        this.observer = observer;
    }

    @Override
    public void detachObserver() {
        this.observer = null;
    }

    @Override
    public void notifyObservers() throws CustomException {
        if (observer == null) {
            throw new CustomException("Notification failed: no observer is currently attached to this wrapper.");
        }
        observer.actionPerformed(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntegerArrayWrapper that = (IntegerArrayWrapper) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntegerArrayWrapper{");
        sb.append("id=").append(id);
        sb.append(", array=").append(Arrays.toString(array));
        sb.append('}');
        return sb.toString();
    }
}
