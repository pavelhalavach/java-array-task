package org.example.arraytask.observer.impl;

import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.observer.ArrayObserver;
import org.example.arraytask.service.ArrayMathService;
import org.example.arraytask.service.impl.ArrayMathServiceImpl;
import org.example.arraytask.warehouse.ArrayParameters;
import org.example.arraytask.warehouse.WareHouse;

public class ArrayObserverImpl implements ArrayObserver {

    @Override
    public void actionPerformed(IntegerArrayWrapper arrayWrapper) throws CustomException {
        ArrayMathService mathService = new ArrayMathServiceImpl();

        int min = mathService.min(arrayWrapper).orElseThrow(() ->
                new CustomException("Cannot calculate minimum: array is empty.")
        );
        int max = mathService.max(arrayWrapper).orElseThrow(() ->
                new CustomException("Cannot calculate maximum: array is empty.")
        );
        long sum = mathService.sum(arrayWrapper).orElseThrow(() ->
                new CustomException("Cannot calculate sum: array is empty.")
        );
        double average = mathService.average(arrayWrapper).orElseThrow(() ->
                new CustomException("Cannot calculate average: array is empty.")
        );

        ArrayParameters arrayParameters = new ArrayParameters(min, max, sum, average);
        WareHouse wareHouse = WareHouse.getInstance();

        wareHouse.put(arrayWrapper.getId(), arrayParameters);
    }
}
