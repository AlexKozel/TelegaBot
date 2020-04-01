package com.example.JavaBot;

import com.example.JavaBot.Entity.CapitalsInfo;
import com.example.JavaBot.Service.CapitalsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

public class Bot extends TelegramLongPollingBot {

    private final Logger LOG = LoggerFactory.getLogger(Bot.class);

    private final String CAPITAL_IS_NOT_FOUND = "Столица не найдена. убедитесь что первая буква заглавная и название столицы указано без ошибок.";
    private final String HELLO_MESSAGE = "Введите название любой столицы, для получения информации...";

    //setter foe Spring DI
    public void setService(CapitalsInfoService service) {
        this.service = service;
    }

    private CapitalsInfoService service;
    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();

        if(message.equalsIgnoreCase("/start")){
            sendMsg(update.getMessage().getChatId().toString(), HELLO_MESSAGE);
            return;
        }
        Optional<CapitalsInfo> capitalsInfo = service.findByName(message);
        if(capitalsInfo.isPresent()){
            sendMsg(update.getMessage().getChatId().toString(), capitalsInfo.get().getDescription());
        } else sendMsg(update.getMessage().getChatId().toString(), CAPITAL_IS_NOT_FOUND);

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
            LOG.error(e.getMessage());
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "StrangeTravelBot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "674324539:AAHCKOgHc_zFjYFVb6tIf4nB1eu7UN3IJV8";
    }

}