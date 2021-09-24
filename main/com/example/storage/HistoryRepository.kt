package com.example.storage

import com.example.service.formatting.FormattingService
import com.example.model.SimpleOperation
import java.util.EnumMap
import java.time.Instant
import com.example.model.OperationModel
import java.util.ArrayList

/**
 * todo hint: you can try to use Kotlin Object to achieve 'singleton' feature
 */

class HistoryRepository(
    private val formattingService: FormattingService,
    private val reverseOrder: Boolean,
    private val showLast: Int,
    private val filterZeroDivision: Boolean
) : HistoryStorage {
    private val history:
            MutableMap<SimpleOperation, MutableList<HistoryEntity>> =
        EnumMap(SimpleOperation::class.java)

    /**
     * !DONE hint: try to use Kotlin collections features
     */
    override fun getHistory(operationType: SimpleOperation): List<String> {
        val operationModels: List<HistoryEntity> = history[operationType] ?: emptyList()
        val history = operationModels
            .sortedByDescending { it.date }
            .filter { !filterZeroDivision || it.operation !== SimpleOperation.DIVIDE && it.b != 0 }
            .map { it.formattedOperation }
            .take(showLast)
        return if (!reverseOrder) history else history.reversed()
    }

    override fun addToHistory(
        operationType: SimpleOperation,
        operationModel: OperationModel,
        result: String
    ) {
        val operationModels = history[operationType]
        val formattedOperation = formattingService.formatOperation(operationType, operationModel, result)
        val historyEntity = HistoryEntity(
            Instant.now(),
            operationType,
            operationModel.a,
            operationModel.b,
            operationModel.c,
            result,
            formattedOperation
        )
        when (operationModels) {
            null -> history[operationType] = mutableListOf(historyEntity)
            else -> operationModels.add(historyEntity)
        }
    }

    /**
     * !DONE hint: you can try to use Kotlin data classes here
     */
    private data class HistoryEntity(
        val date: Instant,
        val operation: SimpleOperation,
        val a: Int,
        val b: Int,
        val c: Int,
        val result: String,
        val formattedOperation: String
    )
}