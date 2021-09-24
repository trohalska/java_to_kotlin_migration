package com.example.service.calc

import com.example.storage.HistoryStorage
import com.example.model.OperationModel
import com.example.model.SimpleOperation
import org.springframework.stereotype.Service
import kotlin.math.sqrt

@Service
class CalculationService(private val historyService: HistoryStorage) : CalculationOperations {

    override fun add(model: OperationModel): String {
        val result = (model.a + model.b).toResult()
        historyService.addToHistory(SimpleOperation.ADD, model, result)
        return result
    }

    override fun subtract(model: OperationModel): String {
        val result = (model.a - model.b).toResult()
        historyService.addToHistory(SimpleOperation.SUBTRACT, model, result)
        return result
    }

    override fun multiply(model: OperationModel): String {
        val result = (model.a * model.b).toResult()
        historyService.addToHistory(SimpleOperation.MULTIPLY, model, result)
        return result
    }

    /**
     * !DONE hint: try to use Kotlin 'when' here
     */
    override fun divide(model: OperationModel) =
         when {
            model.b == 0 -> {
                val result = "Zero division! Your arguments are: A:${model.a}, B:${model.b}"
                historyService.addToHistory(SimpleOperation.DIVIDE, model, result)
                result
            }
            model.a == 0 -> "0"
            else -> {
                val result = (model.a.toDouble() / model.b).toResult()
                historyService.addToHistory(SimpleOperation.DIVIDE, model, result)
                result
            }
        }

    override fun factorial(model: OperationModel) = ((model.c).factorial()).toResult()

    override fun fibonacci(model: OperationModel) = ((model.c).fibonacci()).toResult()

    override fun sqrt(model: OperationModel) = (sqrt(model.c.toDouble())).toResult()

    override fun power(model: OperationModel) = (model.c * model.c).toResult()

    override fun resetCalculator(model: OperationModel) = model.copy(a = 0, b = 0)

    override fun resetAdvancedCalculator(model: OperationModel) = model.copy(c = 0)

    /**
     * !DONE hint: practice with recursive functions + make extension functions
     */
    private fun Int.factorial(): Int =
        if (this >= 1) this * (this - 1).factorial() else 1

    private fun Int.fibonacci(): Int =
        when (this) {
            0 -> 0
            1, 2 -> 1
            else -> (this - 2).fibonacci() + (this - 1).fibonacci()
        }

    private fun Number.toResult() =  this.toString()
}