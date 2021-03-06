package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.DistanceDAO;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Event;
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

    public void delete(Integer distanceId) {
        distanceDAO.deleteById(distanceId);
    }

    public List<Distance> getAll() {
        return distanceDAO.findAll();
    }

    public List<Distance> getAllByEvent(Integer eventId) {
        return distanceDAO.getAllByEvent(eventId);
    }

    public boolean isDistanceAvailable(Distance distance){
        RecordStatus distanceRecordStatus = distance.getRecordStatus();
        if (distanceRecordStatus != RecordStatus.ACTIVE)
            return false;
        Event event = distance.getEvent();
        RecordStatus eventRecordStatus = event.getRecordStatus();
        if (eventRecordStatus != RecordStatus.ACTIVE)
            return false;
        return true;
    }

    public boolean isDistanceDateHasCome(Distance distance){
        Date distanceDate = distance.getDate();
        Date now = new Date();
        return now.after(distanceDate);
    }

    public Distance softDelete(Distance distance) {
        distance.setRecordStatus(RecordStatus.DELETED);
        return update(distance);
    }

    public Distance softDelete(Integer distanceId) {
        if (distanceId != null) {
            Distance distance = distanceDAO.getOne(distanceId);
            return softDelete(distance);
        }
        return null;
    }

}
