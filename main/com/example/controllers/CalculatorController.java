package com.example.controllers;

import com.example.model.OperationModel;
import com.example.model.SimpleOperation;
import com.example.service.calc.CalculationOperations;
import com.example.storage.HistoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculatorController {

    /**
     * hint: try to rewrite to constructor way and practice with Kotlin annotations on constructor arguments
     */
    @Autowired
    private CalculationOperations calculationService;
    @Autowired
    private HistoryStorage historyService;

    @RequestMapping("/calculator")
    public String getCalculatorPage(Model model) {
        model.addAttribute("operationModel", new OperationModel());
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "add")
    public String add(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        /*
         * hint: you can use default values for method parameters
         */
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().add(operationModel));
        model.addAttribute("history", getHistoryService().getHistory(SimpleOperation.ADD));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "subtract")
    public String subtract(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        /**
         * hint: try to practice with Kotlin extension methods here to make code shorter
         */
        model.addAttribute("result", getCalculationService().subtract(operationModel));
        model.addAttribute("history", getHistoryService().getHistory(SimpleOperation.SUBTRACT));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "multiply")
    public String multiply(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().multiply(operationModel));
        model.addAttribute("history", getHistoryService().getHistory(SimpleOperation.MULTIPLY));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "divide")
    public String divide(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().divide(operationModel));
        model.addAttribute("history", getHistoryService().getHistory(SimpleOperation.DIVIDE));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "fibonacci")
    public String fibonacci(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().fibonacci(operationModel));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "sqrt")
    public String sqrt(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().sqrt(operationModel));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "power")
    public String power(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().power(operationModel));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "factorial")
    public String factorial(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("result", getCalculationService().factorial(operationModel));
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "resetCalculator")
    public String resetCalculator(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("operationModel", getCalculationService().resetCalculator(operationModel));
        model.addAttribute("result", 0);
        return TEMPLATE_PAGE_CALCULATOR;
    }

    @PostMapping(value = "/calculator", params = "resetAdvancedCalculator")
    public String resetAdvancedCalculator(@ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (operationModel == null) {
            operationModel = new OperationModel();
        }
        model.addAttribute("operationModel", getCalculationService().resetAdvancedCalculator(operationModel));
        model.addAttribute("result", 0);
        return TEMPLATE_PAGE_CALCULATOR;
    }

    public CalculationOperations getCalculationService() {
        return calculationService;
    }

    public void setCalculationService(CalculationOperations calculationService) {
        this.calculationService = calculationService;
    }

    public HistoryStorage getHistoryService() {
        return historyService;
    }

    public void setHistoryService(HistoryStorage historyService) {
        this.historyService = historyService;
    }

    private static final String TEMPLATE_PAGE_CALCULATOR = "calculator";

}
