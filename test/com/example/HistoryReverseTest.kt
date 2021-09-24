package com.example

import com.example.model.OperationModel
import com.example.model.SimpleOperation
import com.example.service.calc.CalculationService
import com.example.service.formatting.FormattingService
import com.example.storage.HistoryRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HistoryReverseTest : StringSpec({
    val reverseOrder = false
    val showLast = 3
    val filterZeroDivision = true

    val history = HistoryRepository(FormattingService(), reverseOrder, showLast, filterZeroDivision)
    val calc = CalculationService(history)

    "12+2" { calc.add(OperationModel(12,2)) shouldBe "14" }
    "0+2" { calc.add(OperationModel(0,2)) shouldBe "2" }
    "12+0" { calc.add(OperationModel(12,0)) shouldBe "12" }
    "0+0" { calc.add(OperationModel(0,0)) shouldBe "0" }
    "12+3" { calc.add(OperationModel(12,3)) shouldBe "15" }
    "125+125" { calc.add(OperationModel(125,125)) shouldBe "250" }

    "history size no more $showLast" {
        history.getHistory(SimpleOperation.ADD).size shouldBe showLast
    }
    "history check reverseOrder is true" {
        history.getHistory(SimpleOperation.ADD).first() shouldBe "125 + 125 = 250"
    }
    "history check filterZeroDivision is true" {
        history.getHistory(SimpleOperation.DIVIDE).find { it.contains("Zero") } shouldBe null
    }
})