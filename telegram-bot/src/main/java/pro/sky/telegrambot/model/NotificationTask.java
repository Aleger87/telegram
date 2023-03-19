package pro.sky.telegrambot.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String message;
    private LocalDateTime dateTimeSendNotification;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;


    public NotificationTask() {

    }


    public NotificationTask(Long chatId, String message, LocalDateTime dateTimeSendNotification) {
        this.chatId = chatId;
        this.message = message;
        this.dateTimeSendNotification = dateTimeSendNotification;
    }


    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTimeSendNotification() {
        return dateTimeSendNotification;
    }

    public Person getPerson() {
        return person;
    }

    public void setDateTimeSendNotification(LocalDateTime dateTimeSendNotification) {
        this.dateTimeSendNotification = dateTimeSendNotification;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", message='" + message + '\'' +
                ", dateTimeSendNotification=" + dateTimeSendNotification +
                ", person=" + person +
                '}';
    }
}
