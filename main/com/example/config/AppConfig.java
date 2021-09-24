package com.example.config;

import com.example.service.formatting.FormattingService;
import com.example.storage.HistoryRepository;
import com.example.storage.HistoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {

    /**
     * hint: you can try to use Spring Kotlin DSL to achieve nice formatting and easy access for beans initialization
     */
    @Bean
    public HistoryStorage historyRepository(
            @Autowired FormattingService formattingService,
            @Autowired Environment env
    ) {
        return new HistoryRepository(
                formattingService,
                env.getProperty("config.reverseOrder", Boolean.class, true),
                env.getProperty("config.showLast", Integer.class, 5),
                env.getProperty("config.filterZeroDivision",  Boolean.class, false)
        );
    }

}
