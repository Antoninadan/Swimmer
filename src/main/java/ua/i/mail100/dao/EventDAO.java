package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Event;

import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<Event, Integer> {
    @Query(nativeQuery = true, value = "SELECT e.* FROM events e WHERE e.modify_date >= :modifyDate " +
            "UNION " +
            "SELECT e.* FROM distances d " +
            "JOIN events e ON e.id = d.event_id " +
            "WHERE d.modify_date >= :modifyDate")
    List<Event> getAllModifiedAfter(@Param("modifyDate") Long modifyDate);
}

