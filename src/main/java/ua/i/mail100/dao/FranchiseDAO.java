package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Franchise;

import java.util.List;

@Repository
public interface FranchiseDAO extends JpaRepository<Franchise, Integer> {
    Franchise getFirstByName(String name);

    @Query(nativeQuery = true, value = "select * from franchises where trim(lower(name)) = trim(lower(:name))")
    Franchise getFirstByNameTrimAndCaseIgnore(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * FROM franchises WHERE modify_date >= :modifyDate")
    List<Franchise> getAll(@Param("modifyDate") Long modifyDate);
}
