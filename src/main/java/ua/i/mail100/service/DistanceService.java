package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.DistanceDAO;
import ua.i.mail100.model.Distance;

import java.util.List;
import java.util.Optional;

@Service
public class DistanceService {
    @Autowired
    DistanceDAO distanceDAO;

    public Distance getById(Integer id) {
        Optional<Distance> distance = distanceDAO.findById(id);
        if (distance.isEmpty()) {
            return null;
        }
        return distance.get();
    }

    public Distance save(Distance distance) {
        if (distance.getId() == null) {
            return distanceDAO.save(distance);
        }
        return null;
    }

    public Distance update(Distance distance) {
        if (distance.getId() != null && distanceDAO.getOne(distance.getId()) != null) {
            return distanceDAO.save(distance);
        }
        return null;
    }

    public void delete(Distance distance) {
        distanceDAO.delete(distance);
    }

    public List<Distance> getAll() {
        return distanceDAO.findAll();
    }
}
