package com.example

import com.example.service.calc.CalculationService
import com.example.service.formatting.FormattingService
import com.example.storage.HistoryRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans

@SpringBootApplication
class CalculatorSpringBoot

fun main(args: Array<String>) {
    runApplication<CalculatorSpringBoot>(*args) {
        addInitializers(beans)
    }
}

/**
 * !DONE hint: you can try to use Spring Kotlin DSL to achieve nice formatting and easy access for beans initialization
 */
val beans = beans {
    bean {
        CalculationService(ref())
    }
    bean("historyRepository") {
        val formattingService = ref<FormattingService>()
        HistoryRepository(
            formattingService,
            env.getProperty("config.reverseOrder", Boolean::class.java, true),
            env.getProperty("config.showLast", Int::class.java, 5),
            env.getProperty("config.filterZeroDivision", Boolean::class.java, false)
        )
    }
}