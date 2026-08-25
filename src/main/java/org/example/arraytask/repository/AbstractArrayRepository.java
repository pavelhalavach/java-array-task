package org.example.arraytask.repository;

import org.example.arraytask.entity.IntegerArrayWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayRepository {
    private List<IntegerArrayWrapper> integerArrayWrapperList;

    protected AbstractArrayRepository(List<IntegerArrayWrapper> integerArrayWrapperList) {
        this.integerArrayWrapperList = integerArrayWrapperList;
    }

    public boolean add(IntegerArrayWrapper integerArrayWrapper) {
        return integerArrayWrapperList.add(integerArrayWrapper);
    }

    public boolean remove(IntegerArrayWrapper integerArrayWrapper) {
        return integerArrayWrapperList.remove(integerArrayWrapper);
    }

    public boolean addAll(Collection<? extends IntegerArrayWrapper> c) {
        return integerArrayWrapperList.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return integerArrayWrapperList.removeAll(c);
    }

    public IntegerArrayWrapper get(int index) {
        return integerArrayWrapperList.get(index);
    }

    public List<IntegerArrayWrapper> getIntegerArrayWrapperList() {
        return new ArrayList<>(integerArrayWrapperList);
    }

    public final void sort(Comparator<IntegerArrayWrapper> comparator) {
        preSortLogic();
        integerArrayWrapperList.sort(comparator);
        postSortLogic();
    }

    protected void preSortLogic() {}

    protected void postSortLogic() {}

    public abstract List<IntegerArrayWrapper> query(ArraySpecification arraySpecification);
}
