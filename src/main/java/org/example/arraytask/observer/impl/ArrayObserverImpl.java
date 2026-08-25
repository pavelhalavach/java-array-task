package org.example.arraytask.observer.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.observer.ArrayObserver;
import org.example.arraytask.service.ArrayMathService;
import org.example.arraytask.service.impl.ArrayMathServiceImpl;
import org.example.arraytask.warehouse.ArrayParameters;
import org.example.arraytask.warehouse.WareHouse;

import java.util.OptionalInt;

public class ArrayObserverImpl implements ArrayObserver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void actionPerformed(IntegerArrayWrapper arrayWrapper) {
        try {
            ArrayMathService mathService = new ArrayMathServiceImpl();
            OptionalInt minOptional = mathService.min(arrayWrapper);
            if (minOptional.isPresent()) {
                int min = minOptional.getAsInt();
                int max = mathService.max(arrayWrapper).getAsInt();
                long sum = mathService.sum(arrayWrapper).getAsLong();
                double average = mathService.average(arrayWrapper).getAsDouble();

                ArrayParameters arrayParameters = new ArrayParameters(min, max, sum, average);
                WareHouse.getInstance().put(arrayWrapper.getId(), arrayParameters);
            } else {
                logger.log(Level.WARN, "Skipping warehouse update for empty array ID: {}", arrayWrapper.getId());
            }
        } catch (CustomException e) {
            logger.log(Level.ERROR, "Error during observer calculation", e);
        }
    }
}
