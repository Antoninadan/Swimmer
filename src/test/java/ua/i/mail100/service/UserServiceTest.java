package ua.i.mail100.service;

import ua.i.mail100.AppRunner;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@SpringJUnitConfig(AppRunner.class)
class UserServiceTest {

    @MockBean
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Test
    void getByLoginAndPassword() {
        User user = new User("testLogin", "testPassword", "testName", "testSurname");
        when(userDAO.getFirstByLoginAndPassword("testLogin", "testPassword")).thenReturn(user);

        User savedUser = userService.getByLoginAndPassword("testLogin", "testPassword");

        assertEquals(savedUser.getFirstName(), "testName");

        verify(userDAO, times(1)).getFirstByLoginAndPassword("testLogin", "testPassword");
    }

    @Test
    void delete() {
        User user = new User("testLogin", "testPassword", "testName", "testSurname");
        doNothing().when(userDAO).delete(any(User.class));

        userService.delete(user);

        verify(userDAO, times(1)).delete(any(User.class));
    }
}