package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Event;

@Repository
public interface DistanceDAO extends JpaRepository<Event, Integer> {
}
