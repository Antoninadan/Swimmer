package ua.i.mail100.dao;

import ua.i.mail100.AppRunner;
import ua.i.mail100.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppRunner.class)
class UserDAOTest {
    private static List<User> users;

    @Autowired
    UserDAO userDAO;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        users.forEach(it -> userDAO.delete(it));
    }

    @Test
    void getAllBySomeFilters() {
        User user = new User("testLogin", "testPassword", "testName", "testSurname");
        User savedUser = userDAO.saveAndFlush(user);
        users.add(savedUser);

        List<User> foundUser = userDAO.getAllBySomeFilters("testName", "testSurname");

        assertNotNull(foundUser);
        assertTrue(!foundUser.isEmpty());
        assertTrue(foundUser.size() == 1);

        assertEquals(foundUser.get(0).getFirstName(), savedUser.getFirstName());

        // example for exception
        try {
            userDAO.deleteById(Integer.MAX_VALUE);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }
}