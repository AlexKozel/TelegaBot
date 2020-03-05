package com.example.JavaBot;

import com.example.JavaBot.Entity.LoadQuestions;
import com.example.JavaBot.Entity.Questions;
import com.example.JavaBot.Entity.QuestionsRepository;
import com.example.JavaBot.TestDb.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class Bot extends TelegramLongPollingBot {

    LoadQuestions loadQuestions;



    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
        System.out.println(message.equals("1"));
        if (message.equals("1")) {
            add(update.getMessage().getChatId().toString());
        }
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param s      Строка, которую необходимот отправить в качестве сообщения.
     */

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "MyBrestBot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "434674213:AAELV8ScfbP7yaoFkUds1wvzBNJSQ2kydMs";
    }

    @Autowired
    public void setLoadQuestions(LoadQuestions loadQuestions){
        this.loadQuestions = loadQuestions;
    }

    public void add(String chatId) {
        Login login = new Login();
        sendMsg(chatId, login.get(chatId,36L));
    }

}