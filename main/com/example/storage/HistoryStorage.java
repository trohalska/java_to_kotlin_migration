package com.example.storage;

import com.example.model.OperationModel;
import com.example.model.SimpleOperation;

import java.util.List;

public interface HistoryStorage {
    
    List<String> getHistory(SimpleOperation operationType);
    
    void addToHistory(SimpleOperation operationType, OperationModel operationModel, String result);
    
}
