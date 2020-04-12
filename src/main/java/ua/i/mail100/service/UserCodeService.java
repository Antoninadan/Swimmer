package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.config.MailConfig;
import ua.i.mail100.dao.CountryDAO;
import ua.i.mail100.dao.UserCodeDAO;
import ua.i.mail100.dao.FranchiseDAO;
import ua.i.mail100.model.User;
import ua.i.mail100.model.UserCode;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.util.RandomUtil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserCodeService {
    @Autowired
    UserCodeDAO userCodeDAO;

    @Autowired
    MailConfig mailConfig;

    @Autowired
    MailService mailService;

    public UserCode getById(Integer id) {
        Optional<UserCode> userCode = userCodeDAO.findById(id);
        if (userCode.isEmpty()) {
            return null;
        }
        return userCode.get();
    }

    public UserCode getByUser(User user) {
        return userCodeDAO.getFirstByUser(user);
    }

    public String getCodeByUser(User user, Long now) {
        return userCodeDAO.getCodeByUser(user.getId(), now);
    }

    public boolean checkCode(User user, String verifiedСode) {
        String codeDB = getCodeByUser(user, new Date().getTime());
        if (codeDB != null && codeDB.equals(verifiedСode)) return true;
        return false;
    }

    public UserCode save(UserCode userCode) {
        if (userCode.getId() == null) {
            Long now = new Date().getTime();
            userCode.setCreateDate(now);
            userCode.setModifyDate(now);
            userCode.setRecordStatus(RecordStatus.ACTIVE);
            userCode.setDeadline(now + mailConfig.durationCodeInSec * 1000);
            userCode.setCode(Integer.toString(RandomUtil.randomFixedLength(mailConfig.numberCodeDigits)));
            return userCodeDAO.save(userCode);
        }
        return null;
    }

    public UserCode update(UserCode userCode) {
        Integer userCodeId = userCode.getId();
        if (userCodeId != null) {
            UserCode savedEarlierUserCode = userCodeDAO.getOne(userCodeId);
            if (savedEarlierUserCode != null) {
                Long now = new Date().getTime();
                userCode.setRecordStatus(savedEarlierUserCode.getRecordStatus());
                userCode.setCreateDate(savedEarlierUserCode.getCreateDate());
                userCode.setModifyDate(now);
                userCode.setDeadline(now + mailConfig.durationCodeInSec * 1000);
                userCode.setCode(Integer.toString(RandomUtil.randomFixedLength(mailConfig.numberCodeDigits)));
                return userCodeDAO.save(userCode);
            }
            return null;
        }
        return null;
    }

    public void saveCodeAndSendMailWithCode(User user) {
        UserCode userCode = getByUser(user);
        UserCode savedUserCode = new UserCode();

        if (userCode == null) {
            userCode = new UserCode();
            userCode.setUser(user);
            savedUserCode = save(userCode);
        } else savedUserCode = update(userCode);

        String code = savedUserCode.getCode();

        String to = user.getLogin();
        String subject = mailConfig.subjectMailWithCode;
        String text = mailConfig.textMailWithCode + code;

        mailService.sendMail(to, subject, text);
    }
}
