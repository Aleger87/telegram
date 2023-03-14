package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private int chatId;

    private String message;
    private LocalDateTime dateTimeSendNotification;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Person person;

    public NotificationTask() {
    }

    public NotificationTask(int chatId, String message, Person person) {
        this.chatId = chatId;
        this.message = message;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public int getChatId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chatId == that.chatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId);
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
