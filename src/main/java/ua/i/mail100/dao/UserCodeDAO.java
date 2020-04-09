package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.Result;
import ua.i.mail100.model.User;
import ua.i.mail100.model.UserCode;

import java.util.List;

@Repository
public interface UserCodeDAO extends JpaRepository<UserCode, Integer> {
    @Query(nativeQuery = true, value = "SELECT code FROM user_codes WHERE user_id = :userId " +
            "and deadline >= :now")
    String getCodeByUser(@Param("userId") Integer userId, @Param("now") Long now);

    UserCode getFirstByUser(User user);
}
