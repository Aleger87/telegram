package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Person;
import pro.sky.telegrambot.repository.PersonRepository;


@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void addPerson(Person person) {
        if (personRepository.findByUserId(person.getUserId()) == 0) {
            personRepository.save(person);
        }


    }
}

