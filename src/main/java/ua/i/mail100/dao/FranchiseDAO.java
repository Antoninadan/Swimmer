package ua.i.mail100.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Franchise;

@Repository
public interface FranchiseDAO extends JpaRepository<Franchise, Integer> {
    Franchise getFirstByName(String name);
}