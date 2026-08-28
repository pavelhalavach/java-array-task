package org.example.arraytask.repository.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.repository.AbstractArrayRepository;
import org.example.arraytask.repository.specification.ArraySpecification;

import java.util.ArrayList;
import java.util.List;

public class ArrayRepository extends AbstractArrayRepository {

    private static ArrayRepository instance;

    private ArrayRepository() {
        super(new ArrayList<>());
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public List<IntegerArrayWrapper> query(ArraySpecification arraySpecification) {
        List<IntegerArrayWrapper> result = new ArrayList<>();

        for (IntegerArrayWrapper wrapper : getIntegerArrayWrapperList()) {
            if (arraySpecification.specify(wrapper)) {
                result.add(wrapper);
            }
        }

        return result;
    }
}
