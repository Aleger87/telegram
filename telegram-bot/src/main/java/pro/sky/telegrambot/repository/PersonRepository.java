package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
   @Query("select count (a.userId) from Person a where a.userId = :userId")
    int findByUserId(@Param("userId") Long userId);

    @Query(value = "select * from person  where person.user_name  = :userName", nativeQuery = true)
    Person findPersonByUserName(@Param("userName") String userName);
}
