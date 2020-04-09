package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.model.User;
import ua.i.mail100.model.UserStatus;
import ua.i.mail100.util.EncodeUtil;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getByLoginAndPassword(String login, String password) {
        return userDAO.getFirstByLoginAndPassword(login, EncodeUtil.encode(password));
    }

    public User getById(Integer id) {
        Optional<User> user = userDAO.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public User save(User user) {
        if (user.getId() == null && noUserWithSameLogin(user.getLogin())) {
            user.setPassword(EncodeUtil.encode(user.getPassword()));
            user.setUserStatus(UserStatus.NEW);
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
            if (savedEarlierUser != null & noUserWithSameLogin(user.getLogin())) {
                user.setPassword(EncodeUtil.encode(user.getPassword()));
                user.setUserStatus(savedEarlierUser.getUserStatus());
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

    public boolean noUserWithSameLogin(String string) {
        if (userDAO.getFirstByLogin(string) == null)
            return true;
        return false;
    }

    public void deleteById(Integer id) {
        userDAO.deleteById(id);
    }

    public int updateUserStatus(User user, UserStatus userStatus) {
        return userDAO.updateUserStatus(user.getId(), userStatus.ordinal());
    }

    public boolean isUserAvailability(User user) {
        UserStatus userStatus = user.getUserStatus();
        RecordStatus recordStatus = user.getRecordStatus();
        if (userStatus == UserStatus.ACTIVE && recordStatus == RecordStatus.ACTIVE)
            return true;
        return false;
    }
}
