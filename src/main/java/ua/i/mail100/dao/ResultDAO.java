package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Result;

import java.util.List;

@Repository
public interface ResultDAO extends JpaRepository<Result, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM results WHERE user_id = :userId " +
            "and distance_id = :distanceId " +
            "and modify_date >= :modifyDate")
    List<Result> getAllForUserAndDistanceAndModifiedAfter(@Param("userId") Integer userId,
                                     @Param("distanceId") Integer distanceId,
                                     @Param("modifyDate") Long modifyDate);

    @Query(nativeQuery = true, value = "SELECT * FROM results WHERE user_id = :userId " +
            "and modify_date >= :modifyDate")
    List<Result> getAllForUserAndModifiedAfter(@Param("userId") Integer userId,
                                                          @Param("modifyDate") Long modifyDate);
}
