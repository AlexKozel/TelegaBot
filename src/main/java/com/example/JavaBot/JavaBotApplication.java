package com.example.JavaBot;

import com.example.JavaBot.Service.CapitalsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class JavaBotApplication {

    private static final Logger LOG = LoggerFactory.getLogger(JavaBotApplication.class);

    public static void main(String[] args) {

        LOG.info("Initialize TelegramAPI context...");
        ApplicationContext context = SpringApplication.run(JavaBotApplication.class, args);
        ApiContextInitializer.init();


        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot singleBot = new Bot();
        singleBot.setService(context.getBean(CapitalsInfoService.class));

        LOG.info("Registration Bot");
        try {
            telegramBotsApi.registerBot(singleBot);
        } catch (TelegramApiRequestException e) {
            LOG.error("Error while initializing bot!", e);
        }
    }
}

