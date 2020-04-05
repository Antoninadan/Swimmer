package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.FavoriteEvent;
import ua.i.mail100.model.Result;

@Repository
public interface FavoriteEventDAO extends JpaRepository<FavoriteEvent, Integer> {
}
