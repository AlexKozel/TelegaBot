package com.example.JavaBot;

import com.example.JavaBot.Entity.LoadQuestions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.FileNotFoundException;


@SpringBootApplication
public class JavaBotApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(JavaBotApplication.class, args);
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new Bot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}


		LoadQuestions loadQuestions = context.getBean(LoadQuestions.class);
		try {
			loadQuestions.load("src/main/resources/question21.txt");
		} catch (FileNotFoundException e){
			System.out.println("File not found");
		}
	}
}
