package com.example.service.calc;

import com.example.model.OperationModel;

public interface CalculationOperations {

    String add(OperationModel model);

    String subtract(OperationModel model);

    String multiply(OperationModel model);

    String divide(OperationModel model);

    String factorial(OperationModel model);

    String fibonacci(OperationModel model);

    String sqrt(OperationModel model); 

    String power(OperationModel model); 

    OperationModel resetCalculator(OperationModel model); 

    OperationModel resetAdvancedCalculator(OperationModel model);
}
