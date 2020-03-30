package com.example.JavaBot;

import com.example.JavaBot.dao.DBinitialization;
import com.example.JavaBot.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


@SpringBootApplication
public class JavaBotApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(JavaBotApplication.class, args);
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot singleBot = new Bot();
        singleBot.setRepository(context.getBean(CapitalRepository.class));
        try {
            telegramBotsApi.registerBot(singleBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }
}

