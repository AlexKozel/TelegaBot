package com.example.JavaBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.FileNotFoundException;


@SpringBootApplication
public class JavaBotApplication {

	@Autowired
	Bot bot;


	public static void main(String[] args) {
		SpringApplication.run(JavaBotApplication.class, args);
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new Bot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}


////		LoadQuestions loadQuestions = context.getBean(LoadQuestions.class);
//
//		try {
//			loadQuestions.load("src/main/resources/question1.txt");
//		} catch (FileNotFoundException e){
//			System.out.println("File not found");
//		}

	}
}
