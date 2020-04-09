package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.i.mail100.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User getFirstByLoginAndPassword(String login, String password);

    User getFirstByLogin(String login);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users SET user_status = :userStatusOrdinal WHERE id = :userId")
    int updateUserStatus(@Param("userId") Integer userId, @Param("userStatusOrdinal") int userStatusOrdinal);
}
