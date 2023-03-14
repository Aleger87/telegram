package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
   @Query("select count (a.userId) from Person a where a.userId = :userId")
    public int findByUserId(@Param("userId") Integer userId);
}
