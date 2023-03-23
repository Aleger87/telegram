package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.model.Person;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.repository.PersonRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnswerImpl implements Answer {

    @Autowired
    NotificationTaskRepository notificationTaskRepository;

    private final int DATE_LENGTH = 16;

    private Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{"+ DATE_LENGTH +"})(\\s)([\\W+]+)");
    @Autowired
    private PersonRepository personRepository;


    @Override
    public String createTask(Update update) {
        if (checkMessage(update.message().text())) {
            addTaskInDb(update.message().text(), update);
            return "Задача добавлена";
        }
        return "Неверный формат";
    }

    @Override
    public HashMap<Long, SendMessage> scheduledAnswer() {
        List<NotificationTask> tasks = new ArrayList<>(notificationTaskRepository.findAll());
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        HashMap<Long, SendMessage> sendMessages = new HashMap<>();


        for (NotificationTask t : tasks) {
            if (t.getDateTimeSendNotification().equals(date)) {
                sendMessages.put(t.getChatId(), new SendMessage(t.getChatId(), t.getPerson().getName() + " " + t.getMessage()));
            }else {
                sendMessages.put(t.getChatId(), new SendMessage(t.getChatId(), t.getPerson().getName() + " " + " Заданий нет, курим бамбук"));
            }
        }

        return sendMessages;
    }

    private void addTaskInDb(String message, Update update) {
        String date = parseData(message, DATE_LENGTH);
        String task = parseData(message, DATE_LENGTH +1);
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        Person person = parsePersonInfo(update);

        NotificationTask notificationTask = new NotificationTask(
                update.message().chat().id(),
                task,
                dateTime
        );
        notificationTask.setPerson(person);
        notificationTaskRepository.save(notificationTask);
    }

    private Person parsePersonInfo(Update update) {
        var info = update.message().from();
        return new Person(info.id(), info.firstName() + " " + info.lastName(), info.username());
    }

    private boolean checkMessage(String s) {
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    private String parseData(String str, int n) {
        if (str != null & n > DATE_LENGTH) {
            return str.length() < n ? str : str.substring(n);
        }
        return str.length() < n ? str : str.substring(0, n);
    }


}
