package org.example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.arraytask.comparator.ArrayComparator;
import org.example.arraytask.creator.IntegerArrayWrapperCreator;
import org.example.arraytask.creator.impl.IntegerArrayWrapperCreatorImpl;
import org.example.arraytask.entity.IntegerArrayWrapper;
import org.example.arraytask.exception.CustomException;
import org.example.arraytask.factory.IntegerArrayWrapperFactory;
import org.example.arraytask.factory.impl.IntegerArrayWrapperFactoryImpl;
import org.example.arraytask.observer.ArrayObserver;
import org.example.arraytask.observer.impl.ArrayObserverImpl;
import org.example.arraytask.parser.IntegerArrayParser;
import org.example.arraytask.parser.impl.IntegerArrayParserImpl;
import org.example.arraytask.reader.ArrayReader;
import org.example.arraytask.reader.impl.ArrayReaderImpl;
import org.example.arraytask.repository.ArrayRepository;
import org.example.arraytask.repository.impl.*;
import org.example.arraytask.service.impl.ArrayMathServiceImpl;
import org.example.arraytask.validator.ArrayValidator;
import org.example.arraytask.validator.impl.ArrayValidatorImpl;
import org.example.arraytask.warehouse.WareHouse;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            String filePath = "src/main/resources/int-arrays.txt";
            ArrayReader reader = new ArrayReaderImpl();
            ArrayValidator validator = new ArrayValidatorImpl();
            IntegerArrayParser parser = new IntegerArrayParserImpl(validator);

            ArrayRepository repository = ArrayRepositoryImpl.getInstance();
            IntegerArrayWrapperFactory arrayFactory = new IntegerArrayWrapperFactoryImpl();
            ArrayObserver observer = new ArrayObserverImpl();
            WareHouse wareHouse = WareHouse.getInstance();
            IntegerArrayWrapperCreator creator = new IntegerArrayWrapperCreatorImpl(arrayFactory, repository, observer);

            List<IntegerArrayWrapper> arrays = new ArrayList<>();
            List<String> rawLines = reader.readLinesFromFile(filePath);
            logger.log(Level.INFO, "Successfully readed file: " + rawLines);
            List<String> validatedLines = rawLines.stream()
                    .filter(validator::isLineValid)
                    .toList();
            logger.log(Level.INFO, "Validated file: " + validatedLines);

            for (String line : validatedLines) {
                logger.log(Level.INFO, "line: " + line);
                int[] parsed = parser.parse(line);
                arrays.add(creator.createAndRegister(parsed));
            }
            logger.log(Level.INFO, arrays);
            logger.log(Level.INFO, repository.getIntegerArrayWrapperList());

            for (IntegerArrayWrapper wrapper : arrays) {
                logger.log(Level.INFO, "wrapper: " + wrapper);
                logger.log(Level.INFO, "Array parameters: " + wareHouse.get(wrapper.getId()));
            }
            IntegerArrayWrapper newWrapper = creator.createAndRegister(new int[]{7, 8, 9});
            logger.log(Level.INFO, "----------------------------");
            logger.log(Level.INFO, "new wrapper added " + newWrapper);
            for (IntegerArrayWrapper wrapper : repository.getIntegerArrayWrapperList()) {
                logger.log(Level.INFO, "wrapper: " + wrapper);
                logger.log(Level.INFO, "Array parameters: " + wareHouse.get(wrapper.getId()));
            }
            logger.log(Level.INFO, "----------------------------");
            newWrapper.set(0, 1000);
            logger.log(Level.INFO, "new wrapper modified " + newWrapper);
            for (IntegerArrayWrapper wrapper : repository.getIntegerArrayWrapperList()) {
                logger.log(Level.INFO, "wrapper: " + wrapper);
                logger.log(Level.INFO, "Array parameters: " + wareHouse.get(wrapper.getId()));
            }
            logger.log(Level.INFO, "================ SPECIFICATIONS ================");
            logger.log(Level.INFO, "ID > 2");
            List<IntegerArrayWrapper> idResult = repository.query(
                    new IdSpecification(2, ComparisonOperator.GREATER));
            idResult.forEach(wrapper ->
                    logger.log(Level.INFO, wrapper.toString())
            );
            logger.log(Level.INFO, "Length > 3");
            List<IntegerArrayWrapper> lengthResult = repository.query(
                    new LengthSpecification(3, ComparisonOperator.GREATER));
            lengthResult.forEach(wrapper ->
                    logger.log(Level.INFO, wrapper.toString())
            );
            logger.log(Level.INFO, "Sum > 10");
            List<IntegerArrayWrapper> sumResult = repository.query(
                    new SumSpecification(10, ComparisonOperator.GREATER, new ArrayMathServiceImpl()));
            sumResult.forEach(wrapper ->
                    logger.log(Level.INFO, wrapper.toString())
            );
            logger.log(Level.INFO, "================ SORT BY ID REVERSED ================");
            repository.sort(ArrayComparator.ID.reversed());
            repository.getIntegerArrayWrapperList()
                    .forEach(wrapper ->
                            logger.log(Level.INFO, wrapper.toString())
                    );
            logger.log(Level.INFO, "================ SORT BY FIRST ELEMENT REVERSED ================");
            repository.sort(ArrayComparator.FIRST_ELEMENT.reversed());
            repository.getIntegerArrayWrapperList()
                    .forEach(wrapper ->
                            logger.log(Level.INFO, wrapper.toString())
                    );
            logger.log(Level.INFO, "================ SORT BY SIZE ================");
            repository.sort(ArrayComparator.SIZE);
            repository.getIntegerArrayWrapperList()
                    .forEach(wrapper ->
                            logger.log(Level.INFO, wrapper.toString())
                    );
        } catch (CustomException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }


}