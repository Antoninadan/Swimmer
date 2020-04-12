package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.config.MailConfig;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.model.User;
import ua.i.mail100.model.UserStatus;
import ua.i.mail100.util.EncodeUtil;
import ua.i.mail100.util.RandomUtil;

import java.util.Date;
import java.util.Formatter;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    MailService mailService;

    @Autowired
    MailConfig mailConfig;

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

    public User getByLogin(String login) {
        User user = userDAO.getFirstByLogin(login);
        if (user == null) {
            return null;
        }
        return user;
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
        System.out.println(user);
        Integer userId = user.getId();
        if (userId != null) {
            Long now = new Date().getTime();
            user.setModifyDate(now);
            return userDAO.save(user);
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

    public boolean isUserExists(User user) {
        if (user == null) return false;

        Integer id = user.getId();
        if (id == null) return false;

        User findedUser = getById(id);
        if (findedUser == null) return false;
        return true;
    }

    public User saveRecoveryPasswordAndSendMail(User user) {
        String newPassword = Integer.toString(RandomUtil.randomFixedLength(8));
        user.setPassword(EncodeUtil.encode(newPassword));
        sendMailPasswordRecovery(user, newPassword);
        User updatedUser = update(user);
        return updatedUser;
    }

    public void sendMailPasswordRecovery(User user, String newPassword) {
        Formatter formatter = new Formatter();
        String to = user.getLogin();
        String subject = mailConfig.subjectMailPasswordRecovery;
        String text = formatter.format(mailConfig.textMailPasswordRecovery, newPassword).
                toString();
        mailService.sendMail(to, subject, text);
    }

}
