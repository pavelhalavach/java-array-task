package org.example.arraytask.repository.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.repository.ArrayRepository;
import org.example.arraytask.repository.ArraySpecification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayRepositoryImpl extends ArrayRepository {

    private static ArrayRepositoryImpl instance;

    private ArrayRepositoryImpl() {
        super(new ArrayList<>());
    }

    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    public List<IntegerArrayWrapper> query(ArraySpecification arraySpecification) throws CustomException {
        List<IntegerArrayWrapper> result = new ArrayList<>();

        for (IntegerArrayWrapper wrapper : getIntegerArrayWrapperList()) {
            if (arraySpecification.specify(wrapper)) {
                result.add(wrapper);
            }
        }

        return result;
    }
}
