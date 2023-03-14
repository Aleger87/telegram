package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.request.SendMessage;

public interface Answer {
    public SendMessage answer(int chatId, String messageText);
}
