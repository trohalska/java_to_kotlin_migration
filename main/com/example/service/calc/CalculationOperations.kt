package com.example.service.calc

import com.example.model.OperationModel

interface CalculationOperations {
    fun add(model: OperationModel): String
    fun subtract(model: OperationModel): String
    fun multiply(model: OperationModel): String
    fun divide(model: OperationModel): String
    fun factorial(model: OperationModel): String
    fun fibonacci(model: OperationModel): String
    fun sqrt(model: OperationModel): String
    fun power(model: OperationModel): String
    fun resetCalculator(model: OperationModel): OperationModel
    fun resetAdvancedCalculator(model: OperationModel): OperationModel
}