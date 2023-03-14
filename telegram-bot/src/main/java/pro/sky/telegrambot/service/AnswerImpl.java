package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class AnswerImpl implements Answer {

    @Override
    public SendMessage answer(int chatId, String messageText) {
        messageText = "jkzzkkzkzhf";
        SendMessage message = new SendMessage(chatId, messageText);
        return message;
    }



}
