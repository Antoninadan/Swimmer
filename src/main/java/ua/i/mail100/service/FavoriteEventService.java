package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.FavoriteEventDAO;
import ua.i.mail100.model.FavoriteEvent;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteEventService {
    @Autowired
    FavoriteEventDAO favoriteEventDAO;

    public FavoriteEvent getById(Integer id) {
        Optional<FavoriteEvent> favoriteEvent = favoriteEventDAO.findById(id);
        if (favoriteEvent.isEmpty()) {
            return null;
        }
        return favoriteEvent.get();
    }

    public FavoriteEvent save(FavoriteEvent favoriteEvent) {
        if (favoriteEvent.getId() == null) {
            return favoriteEventDAO.save(favoriteEvent);
        }
        return null;
    }

    public FavoriteEvent update(FavoriteEvent favoriteEvent) {
        if (favoriteEvent.getId() != null && favoriteEventDAO.getOne(favoriteEvent.getId()) != null) {
            return favoriteEventDAO.save(favoriteEvent);
        }
        return null;
    }

    public void delete(FavoriteEvent favoriteEvent) {
        favoriteEventDAO.delete(favoriteEvent);
    }

    public List<FavoriteEvent> getAll() {
        return favoriteEventDAO.findAll();
    }
}
