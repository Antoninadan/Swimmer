package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Event;

import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<Event, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM events WHERE modify_date >= :modifyDate")
    List<Event> getAll(@Param("modifyDate") Long modifyDate);
}
