package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.CountryDAO;
import ua.i.mail100.dao.UserDAO;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Country;
import ua.i.mail100.util.PasswordUtil;

import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    CountryDAO countryDAO;


    public Country getById(Integer id) {
       Optional<Country> country = countryDAO.findById(id);
        if (country.isEmpty()) {
            return null;
        }
        return country.get();
    }

    public Country save(Country country) {
        if (country.getId() == null && countryDAO.getFirstByName(country.getName()) == null) {
            return countryDAO.save(country);
        }
        return null;
    }

    public Country update(Country country) {
        if (country.getId() != null && countryDAO.getOne(country.getId()) != null) {
            return countryDAO.save(country);
        }
        return null;
    }

    public void delete(Country country) {
        countryDAO.delete(country);
    }

}
