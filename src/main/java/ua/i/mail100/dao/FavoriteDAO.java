package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Favorite;

import java.util.List;

@Repository
public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM favorite_events WHERE user_id = :userId " +
            "and modify_date >= :modifyDate")
            List<Favorite> getAllByUserAndModifiedAfter(@Param("userId") Integer userId,
                    @Param("modifyDate") Long modifyDate);


}
