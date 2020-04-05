package ua.i.mail100.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ua.i.mail100.AppRunner;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.model.Sex;
import ua.i.mail100.model.User;
import ua.i.mail100.model.UserStatus;
import ua.i.mail100.util.PasswordUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(AppRunner.class)
class UserServiceTest {
    @MockBean
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Test
    void getByLoginAndPassword() {
        User user = new User(1, "testLogin", "testPassword", "testName",
                Sex.FEMALE, 4525463L, UserStatus.ACTIVE, 5655464L, 11L, RecordStatus.ACTIVE);
        when(userDAO.getFirstByLoginAndPassword("testLogin", PasswordUtil.encodePassword("testPassword"))).
                thenReturn(user);

        User savedUser = userService.getByLoginAndPassword("testLogin", "testPassword");
        assertEquals("testName", savedUser.getName());
        verify(userDAO, times(1)).
                getFirstByLoginAndPassword("testLogin", PasswordUtil.encodePassword("testPassword"));
    }

    @Test
    void getById() {
        User user = new User(1, "testLogin", "testPassword", "testName",
                Sex.FEMALE, 4525463L, UserStatus.ACTIVE, 5655464L, 11L, RecordStatus.ACTIVE);
        Optional<User> userOptimal = Optional.of(user);
        when(userDAO.findById(1)).thenReturn(userOptimal);

        User findedUser = userService.getById(1);
        assertEquals("testName", findedUser.getName());
        verify(userDAO, times(1)).
                findById(1);
    }

    @Test
    void getByIdNullTest() {
        User user = null;
        when(userDAO.findById(1)).thenReturn(Optional.ofNullable(user));

        User findedUser = userService.getById(1);
        assertEquals(null, findedUser);
        verify(userDAO, times(1)).findById(1);
    }

    @Test
    void save() {
        User user = new User(null, "testLogin", "testPassword", "testName",
                Sex.FEMALE, 4525463L, UserStatus.ACTIVE, 5655464L, 11L, RecordStatus.ACTIVE);
        User userToSave = new User(null, "testLogin", "testPassword", "testName",
                Sex.FEMALE, 4525463L, UserStatus.ACTIVE, 5655464L, 11L, RecordStatus.ACTIVE);
        userToSave.setPassword(PasswordUtil.encodePassword(user.getPassword()));
        userToSave.setId(1);
        when(userDAO.save(user)).thenReturn(userToSave);

        User savedUser = userService.save(user);
        assertEquals(1, savedUser.getId());
        verify(userDAO, times(1)).save(user);
    }

    @Test
    void saveNotNullId() {
        User user = new User(1, "testLogin", "testPassword", "testName",
                Sex.FEMALE, 4525463L, UserStatus.ACTIVE, 5655464L, 11L, RecordStatus.ACTIVE);

        User savedUser = userService.save(user);
        assertEquals(null, savedUser);
    }

    @Test
    void saveNullLogin() {
        User user = new User(1, null, "testPassword", "testName",
                Sex.FEMALE, 4525463L, UserStatus.ACTIVE, 5655464L, 11L, RecordStatus.ACTIVE);

        User savedUser = userService.save(user);
        assertEquals(null, savedUser);
    }
}