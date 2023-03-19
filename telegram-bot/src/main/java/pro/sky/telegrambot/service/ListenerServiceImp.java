package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Person;

import java.util.HashMap;

@Service
public class ListenerServiceImp implements ListenerService{

    private final String START = "/start";

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private AnswerImpl answer;


    @Override
    public SendMessage listener(Update update) {
        if (update.message().text().equals(START)) {
            return new SendMessage(update.message().chat().id(), checkPerson(update));
        }else{
            return new SendMessage(update.message().chat().id(), answer.createTask(update));
        }
    }

    @Override
    public HashMap<Long, SendMessage> scheduledAnswer() {
        return answer.scheduledAnswer();
    }



    private String checkPerson(Update update) {
        personService.addPerson(new Person(update.message().from().id(),
                update.message().from().firstName() + " " + update.message().from().lastName(),
                update.message().from().username()));

        String hello = "Привет " + update.message().from().firstName() + " " + update.message().from().lastName() +
                " добро пожаловать в чат";
        return hello;
    }

}
