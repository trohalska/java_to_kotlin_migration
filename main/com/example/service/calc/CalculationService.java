package com.example.service.calc;

import com.example.model.OperationModel;
import com.example.model.SimpleOperation;
import com.example.storage.HistoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.utils.StringUtils.toResult;

@Service
public class CalculationService implements CalculationOperations {

    @Autowired
    private HistoryStorage historyService;

    @Override
    public String add(OperationModel model) {
        String result = toResult(model.getA() + model.getB());
        getHistoryService().addToHistory(SimpleOperation.ADD, model, result);
        return result;
    }

    @Override
    public String subtract(OperationModel model) {
        String result = toResult(model.getA() - model.getB());
        getHistoryService().addToHistory(SimpleOperation.SUBTRACT, model, result);
        return result;
    }

    @Override
    public String multiply(OperationModel model) {
        String result = toResult(model.getA() * model.getB());
        getHistoryService().addToHistory(SimpleOperation.MULTIPLY, model, result);
        return result;
    }

    /**
     * hint: try to use Kotlin 'when' here
     */
    @Override
    public String divide(OperationModel model) {
        if (model.getA() == 0) return toResult(0);
        if (model.getB() == 0) {
            String result = "Zero division! Your arguments are: A:" + model.getA() + ", B:" + model.getB();
            getHistoryService().addToHistory(SimpleOperation.DIVIDE, model, result);
            return result;
        }
        String result = toResult((double) model.getA() / model.getB());
        getHistoryService().addToHistory(SimpleOperation.DIVIDE, model, result);
        return result;
    }

    @Override
    public String factorial(OperationModel model) {
        return toResult(factorial(model.getC()));
    }

    /**
     * hint: practice with recursive functions
     */
    private int factorial(int n) {
        return n < 2 ? n : n * factorial(n - 1);
    }

    @Override
    public String fibonacci(OperationModel model) {
        return toResult(fibonacci(model.getC()));
    }

    /**
     * hint: practice with recursive functions
     */
    private int fibonacci(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else if (n == 2) return 1;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Override
    public String sqrt(OperationModel model) {
        return toResult(Math.sqrt(model.getC()));
    }

    @Override
    public String power(OperationModel model) {
        return toResult(model.getC() * model.getC());
    }

    @Override
    public OperationModel resetCalculator(OperationModel model) {
        model.setA(0);
        model.setB(0);
        return model;
    }

    @Override
    public OperationModel resetAdvancedCalculator(OperationModel model) {
        model.setC(0);
        return model;
    }

    public HistoryStorage getHistoryService() {
        return historyService;
    }

    public void setHistoryService(HistoryStorage historyService) {
        this.historyService = historyService;
    }

}
