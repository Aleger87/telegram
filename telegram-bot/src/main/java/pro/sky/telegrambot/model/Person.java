package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Long userId;

    private  String name;
    private  String userName;
    private LocalDateTime dateTimeOfReg;



    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<NotificationTask> notifications;


    public Person(Long userId, String name, String userName) {
        this.userId = userId;
        this.name = name;
        this.userName = userName;
        this.dateTimeOfReg = LocalDateTime.now();
    }

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getDateTimeOfReg() {
        return dateTimeOfReg;
    }
    public Set<NotificationTask> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<NotificationTask> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return userId == person.userId && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", dateTime=" + dateTimeOfReg +
                '}';
    }
}
