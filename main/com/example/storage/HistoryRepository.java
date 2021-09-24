package com.example.storage;

import com.example.model.OperationModel;
import com.example.model.SimpleOperation;
import com.example.service.formatting.FormattingService;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * hint: you can try to use Kotlin Object to achieve 'singleton' feature
 */
public class HistoryRepository implements HistoryStorage {

    public HistoryRepository(FormattingService formattingService, boolean reverseOrder, int showLast, boolean filterZeroDivision) {
        this.formattingService = formattingService;
        this.reverseOrder = reverseOrder;
        this.showLast = showLast;
        this.filterZeroDivision = filterZeroDivision;
    }

    private FormattingService formattingService;
    private final boolean reverseOrder;
    private final int showLast;
    private final boolean filterZeroDivision;

    private final Map<SimpleOperation, List<HistoryEntity>> history = new EnumMap<>(SimpleOperation.class);

    /**
     * hint: try to use Kotlin collections features
     */
    @Override
    public List<String> getHistory(SimpleOperation operationType) {
        List<HistoryEntity> operationModels = history.get(operationType);
        if (operationModels == null) {
            return Collections.emptyList();
        } else {
            ArrayList<HistoryEntity> historyToReturn = new ArrayList<>(operationModels);
            if(reverseOrder) {
                Collections.reverse(historyToReturn);
            }
            return historyToReturn
                    .stream()
                    .limit(showLast)
                    .sorted(reverseOrder ? Comparator.comparing(HistoryEntity::getDate) : Comparator.comparing(HistoryEntity::getDate).reversed())
                    .filter(filterZeroDivision ? ( it -> it.operation != SimpleOperation.DIVIDE && it.b != 0) : ( i -> true))
                    .map(HistoryEntity::getFormattedOperation)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void addToHistory(SimpleOperation operationType, OperationModel operationModel, String calculationResult) {
        List<HistoryEntity> operationModels = history.get(operationType);
        String formattedOperation = formattingService.formatOperation(operationType, operationModel, calculationResult);
        HistoryEntity historyEntity = new HistoryEntity(
                Instant.now(),
                operationType,
                operationModel.getA(),
                operationModel.getB(),
                operationModel.getC(),
                calculationResult,
                formattedOperation
        );
        if(operationModels == null) {
            List<HistoryEntity> newOperationHistory = new ArrayList<>();
            newOperationHistory.add(historyEntity);
            history.put(operationType, newOperationHistory);
        } else {
            operationModels.add(historyEntity);
        }
    }

    public FormattingService getFormattingService() {
        return formattingService;
    }

    public void setFormattingService(FormattingService formattingService) {
        this.formattingService = formattingService;
    }

    /**
     * hint: you can try to use Kotlin data classes here
     */
    private static class HistoryEntity {
    
        private Instant date;
        private SimpleOperation operation;
        private int a;
        private int b;
        private int c;
        private String result;
        private String formattedOperation;

        public HistoryEntity(Instant date, SimpleOperation operation, int a, int b, int c, String result, String formattedOperation) {
            this.date = date;
            this.operation = operation;
            this.a = a;
            this.b = b;
            this.c = c;
            this.result = result;
            this.formattedOperation = formattedOperation;
        }

        public Instant getDate() {
            return date;
        }

        public void setDate(Instant date) {
            this.date = date;
        }

        public SimpleOperation getOperation() {
            return operation;
        }

        public void setOperation(SimpleOperation operation) {
            this.operation = operation;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getFormattedOperation() {
            return formattedOperation;
        }

        public void setFormattedOperation(String formattedOperation) {
            this.formattedOperation = formattedOperation;
        }
    }
}
