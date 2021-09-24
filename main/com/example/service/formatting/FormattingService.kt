package com.example.service.formatting

import com.example.model.SimpleOperation
import com.example.model.OperationModel
import org.springframework.stereotype.Service

@Service
class FormattingService {
    fun formatOperation(
        operationType: SimpleOperation,
        operationModel: OperationModel,
        result: String
    ) = "${operationModel.a} ${operationType.sign} ${operationModel.b} = $result"
}