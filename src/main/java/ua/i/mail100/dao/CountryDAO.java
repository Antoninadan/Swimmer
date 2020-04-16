package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.User;

import java.util.List;

@Repository
public interface CountryDAO extends JpaRepository<Country, Integer> {
    Country getFirstByName(String name);

    @Query(nativeQuery = true, value = "select * from countries where trim(lower(name)) = trim(lower(:name))")
    Country getFirstByNameTrimAndCaseIgnore(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * FROM countries WHERE modify_date >= :modifyDate")
    List<Country> getAll(@Param("modifyDate") Long modifyDate);
}
