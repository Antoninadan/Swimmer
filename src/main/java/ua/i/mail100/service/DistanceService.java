package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.DistanceDAO;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.RecordStatus;

import java.util.Date;
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
            Long now = new Date().getTime();
            distance.setCreateDate(now);
            distance.setModifyDate(now);
            distance.setRecordStatus(RecordStatus.ACTIVE);
            return distanceDAO.save(distance);
        }
        return null;
    }

    public Distance update(Distance distance) {
        Integer distanceId = distance.getId();
        if (distanceId != null) {
            Distance savedEarlierDistance = distanceDAO.getOne(distanceId);
            if (savedEarlierDistance != null) {
                Long now = new Date().getTime();
                distance.setRecordStatus(savedEarlierDistance.getRecordStatus());
                distance.setCreateDate(savedEarlierDistance.getCreateDate());
                distance.setModifyDate(now);
                return distanceDAO.save(distance);
            }
            return null;
        }
        return null;
    }

    public void delete(Distance distance) {
        distanceDAO.delete(distance);
    }

    public List<Distance> getAll() {
        return distanceDAO.findAll();
    }

    public List<Distance> getAllByEvent(Integer distanceId) {
        return distanceDAO.getAllByEvent(distanceId);
    }
}
