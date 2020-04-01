package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.User;

@Repository
public interface CountryDAO extends JpaRepository<Country, Integer> {
    Country getFirstByName(String name);
}
