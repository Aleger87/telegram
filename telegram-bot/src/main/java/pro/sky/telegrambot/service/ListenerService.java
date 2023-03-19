package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.HashMap;

public interface ListenerService {
    SendMessage listener(Update u);

    HashMap<Long,SendMessage> scheduledAnswer();
}
