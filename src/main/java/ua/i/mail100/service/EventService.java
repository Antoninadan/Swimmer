package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.CountryDAO;
import ua.i.mail100.dao.EventDAO;
import ua.i.mail100.dao.FranchiseDAO;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.Event;
import ua.i.mail100.util.PasswordUtil;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventDAO eventDAO;

    @Autowired
    FranchiseDAO franchiseDAO;

    @Autowired
    CountryDAO countryDAO;

    public Event getById(Integer id) {
        Optional<Event> event = eventDAO.findById(id);
        if (event.isEmpty()) {
            return null;
        }
        return event.get();
    }

    public Event save(Event event) {
        if (event.getId() == null) {
            return eventDAO.save(event);
        }
        return null;
    }

    public Event update(Event event) {
        if (event.getId() != null && eventDAO.getOne(event.getId()) != null) {
            return eventDAO.save(event);
        }
        return null;
    }

    public void delete(Event event) {
        eventDAO.delete(event);
    }

    public List<Event> getAll() {
        return eventDAO.findAll();
    }
}
