package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Event;

import java.util.List;

@Repository
public interface DistanceDAO extends JpaRepository<Distance, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM distances " +
            "WHERE event_id = :eventId ")
    List<Distance> getAllByEvent(@Param("eventId") Integer eventId);
}
