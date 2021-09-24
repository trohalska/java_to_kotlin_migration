package com.example.service.formatting;

import com.example.model.OperationModel;
import com.example.model.SimpleOperation;
import org.springframework.stereotype.Service;

@Service
public class FormattingService {

    public String formatOperation(SimpleOperation operationType, OperationModel operationModel, String result) {
        StringBuilder operationString = new StringBuilder();
        operationString.append(operationModel.getA());
        operationString.append(' ');
        operationString.append(operationType.getSign());
        operationString.append(' ');
        operationString.append(operationModel.getB());
        operationString.append(' ');
        operationString.append('=');
        operationString.append(' ');
        operationString.append(result);
        return operationString.toString();
    }

}
