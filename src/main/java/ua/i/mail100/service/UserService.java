package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.User;
import ua.i.mail100.util.PasswordUtil;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;


    public User getByLoginAndPassword(String login, String password) {
        return userDAO.getFirstByLoginAndPassword(login, PasswordUtil.encodePassword(password));
    }

    public User getById(Integer id) {
       Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public User save(User user) {
        if (user.getId() == null && userDAO.getFirstByLogin(user.getLogin()) == null) {
            return userDAO.save(user);
        }
        return null;
    }

    public User update(User user) {
        if (user.getId() != null && userDAO.getOne(user.getId()) != null) {
            return userDAO.save(user);
        }
        return null;
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

}
