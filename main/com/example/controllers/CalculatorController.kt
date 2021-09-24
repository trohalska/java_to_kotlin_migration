package com.example.controllers

import com.example.model.OperationModel
import com.example.model.SimpleOperation
import com.example.service.calc.CalculationOperations
import com.example.storage.HistoryStorage
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * !DONE hint: try to rewrite to constructor way and practice with Kotlin annotations on constructor arguments
 * !DONE hint: you can use default values for method parameters
 * !DONE hint: try to practice with Kotlin extension methods here to make code shorter
 */

@Controller
class CalculatorController(
    private val calculationService: CalculationOperations,
    private val historyService: HistoryStorage
) {

    @RequestMapping("/calculator")
    fun getCalculatorPage(model: Model): String {
        model.addAttributesWithDefaultValues(OperationModel())
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["add"])
    fun add(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(operationModel, calculationService.add(operationModel), historyService.getHistory(SimpleOperation.ADD))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["subtract"])
    fun subtract(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(operationModel, calculationService.subtract(operationModel), historyService.getHistory(SimpleOperation.SUBTRACT))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["multiply"])
    fun multiply(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(operationModel, calculationService.multiply(operationModel), historyService.getHistory(SimpleOperation.MULTIPLY))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["divide"])
    fun divide(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(operationModel, calculationService.divide(operationModel), historyService.getHistory(SimpleOperation.DIVIDE))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["fibonacci"])
    fun fibonacci(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(operationModel, calculationService.fibonacci(operationModel))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["sqrt"])
    fun sqrt(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttribute("result", calculationService.sqrt(operationModel))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["power"])
    fun power(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttribute("result", calculationService.power(operationModel))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["factorial"])
    fun factorial(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttribute("result", calculationService.factorial(operationModel))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["resetCalculator"])
    fun resetCalculator(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(calculationService.resetCalculator(operationModel))
        return TEMPLATE_PAGE_CALCULATOR
    }

    @PostMapping(value = ["/calculator"], params = ["resetAdvancedCalculator"])
    fun resetAdvancedCalculator(
        @ModelAttribute("operationModel") operationModel: OperationModel = OperationModel(),
        model: Model
    ): String {
        model.addAttributesWithDefaultValues(calculationService.resetAdvancedCalculator(operationModel))
        return TEMPLATE_PAGE_CALCULATOR
    }

    companion object {
        private const val TEMPLATE_PAGE_CALCULATOR = "calculator"
    }

    private fun Model.addAttributesWithDefaultValues(
        operationModel: OperationModel,
        result: String = "",
        history: List<String> = emptyList()
    ) {
        this.addAttribute("operationModel", operationModel)
        this.addAttribute("result", result)
        this.addAttribute("history", history)
    }
}