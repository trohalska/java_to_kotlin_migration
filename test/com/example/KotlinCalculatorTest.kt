package com.example

import com.example.model.OperationModel
import com.example.model.SimpleOperation
import com.example.service.calc.CalculationService
import com.example.service.formatting.FormattingService
import com.example.storage.HistoryRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class KotlinCalculatorTest : StringSpec({
    val reverseOrder = true
    val showLast = 5
    val filterZeroDivision = false

    val history = HistoryRepository(FormattingService(), reverseOrder, showLast, filterZeroDivision)
    val calc = CalculationService(history)

    "12+2" { calc.add(OperationModel(12,2)) shouldBe "14" }
    "0+2" { calc.add(OperationModel(0,2)) shouldBe "2" }
    "12+0" { calc.add(OperationModel(12,0)) shouldBe "12" }
    "0+0" { calc.add(OperationModel(0,0)) shouldBe "0" }

    "12-2" { calc.subtract(OperationModel(12,2)) shouldBe "10" }
    "0-2" { calc.subtract(OperationModel(0,2)) shouldBe "-2" }
    "12-0" { calc.subtract(OperationModel(12,0)) shouldBe "12" }
    "0-0" { calc.subtract(OperationModel(0,0)) shouldBe "0" }

    "12*2" { calc.multiply(OperationModel(12,2)) shouldBe "24" }
    "0*2" { calc.multiply(OperationModel(0,2)) shouldBe "0" }
    "12*0" { calc.multiply(OperationModel(12,0)) shouldBe "0" }
    "0*0" { calc.multiply(OperationModel(0,0)) shouldBe "0" }

    "12/2" { calc.divide(OperationModel(12,2)) shouldBe "6.0" }
    "0/2" { calc.divide(OperationModel(0,2)) shouldBe "0" }
    "12/0" { calc.divide(OperationModel(12,0)) shouldBe "Zero division! Your arguments are: A:12, B:0" }
    "0/0" { calc.divide(OperationModel(0,0)) shouldBe "Zero division! Your arguments are: A:0, B:0" }

    "fibonacci 12" { calc.fibonacci(OperationModel(12)) shouldBe "144" }
    "factorial 12" { calc.factorial(OperationModel(12)) shouldBe "479001600" }
    "sqrt 12" { calc.sqrt(OperationModel(12)) shouldBe "3.4641016151377544" }
    "power 12" { calc.power(OperationModel(12)) shouldBe "144" }

    "reset calc" { calc.resetCalculator(OperationModel(12, 12, 12)) shouldBe OperationModel(0,0,12) }
    "reset advanced" { calc.resetAdvancedCalculator(OperationModel(12, 12, 12)) shouldBe OperationModel(12,12,0) }

    /**
     * CHECK HISTORY
     */

    "history size" { history.getHistory(SimpleOperation.ADD).size shouldBe 4 }

    "history size no more $showLast" {
        calc.add(OperationModel(12,2)) shouldBe "14"
        calc.add(OperationModel(125,125)) shouldBe "250"
        calc.add(OperationModel(125,125)) shouldBe "250"
        calc.add(OperationModel(125,125)) shouldBe "250"
        history.getHistory(SimpleOperation.ADD).size shouldBe showLast
    }
    "history check reverseOrder is true" {
        calc.add(OperationModel(125,125)) shouldBe "250"
        history.getHistory(SimpleOperation.ADD).last() shouldBe "125 + 125 = 250"
    }
    "history check filterZeroDivision is false" {
        history.getHistory(SimpleOperation.DIVIDE).find { it.contains("Zero") } shouldContain "Zero"
    }
})