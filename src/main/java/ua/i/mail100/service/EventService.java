package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.CountryDAO;
import ua.i.mail100.dao.FranchiseDAO;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.Event;
import ua.i.mail100.util.PasswordUtil;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    FranchiseDAO franchiseDAO;
    
    @Autowired
    CountryDAO countryDAO;  
      
    public Event getById(Integer id) {
       Optional<Event> user = userDAO.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public Event save(Event user) {
        if (user.getId() == null && userDAO.getFirstByLogin(user.getLogin()) == null) {
            user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
            return userDAO.save(user);
        }
        return null;
    }

    public Event update(Event user) {
        if (user.getId() != null && userDAO.getOne(user.getId()) != null) {
            user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
            return userDAO.save(user);
        }
        return null;
    }

    public void delete(Event user) {
        userDAO.delete(user);
    }

}
