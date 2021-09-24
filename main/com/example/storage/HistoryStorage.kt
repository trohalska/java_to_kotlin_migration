package com.example.storage

import com.example.model.SimpleOperation
import com.example.model.OperationModel

interface HistoryStorage {
    fun getHistory(operationType: SimpleOperation): List<String>
    fun addToHistory(operationType: SimpleOperation, operationModel: OperationModel, result: String)
}