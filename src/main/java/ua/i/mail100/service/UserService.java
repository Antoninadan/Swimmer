package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.model.User;
import ua.i.mail100.util.PasswordUtil;

import java.util.Date;
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
        if (user.getId() == null && noUserWithSameLogin(user)) {
            user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
            Long now = new Date().getTime();
            user.setCreateDate(now);
            user.setModifyDate(now);
            user.setRecordStatus(RecordStatus.ACTIVE);
            return userDAO.save(user);
        }
        return null;
    }

    public User update(User user) {
        Integer userId = user.getId();
        if (userId != null) {
            User savedEarlierUser = userDAO.getOne(userId);
            if (savedEarlierUser != null & noUserWithSameLogin(user)) {
                user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
                Long now = new Date().getTime();
                user.setRecordStatus(savedEarlierUser.getRecordStatus());
                user.setCreateDate(savedEarlierUser.getCreateDate());
                user.setModifyDate(now);
                return userDAO.save(user);
            }
            return null;
        }
        return null;
    }

    private boolean noUserWithSameLogin(User user) {
        if (userDAO.getFirstByLogin(user.getLogin()) == null)
            return true;
        return false;
    }

    public void deleteById (Integer id) {
        userDAO.deleteById(id);
    }

}
