package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User getFirstByLoginAndPassword(String login, String password);

    User getFirstByLogin(String login);
}
