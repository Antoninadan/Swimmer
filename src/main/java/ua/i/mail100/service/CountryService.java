package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.CountryDAO;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.RecordStatus;

import java.util.Date;
import java.util.List;
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
        if (country.getId() == null && noCountryWithSameName(country)) {
            Long now = new Date().getTime();
            country.setCreateDate(now);
            country.setModifyDate(now);
            country.setRecordStatus(RecordStatus.ACTIVE);
            return countryDAO.save(country);
        }
        return null;
    }

    public Country update(Country country) {
        Integer countryId = country.getId();
        if (countryId != null) {
            Country savedEarlierCountry = countryDAO.getOne(countryId);
            if (savedEarlierCountry != null & noCountryWithSameName(country)) {
                Long now = new Date().getTime();
                country.setRecordStatus(savedEarlierCountry.getRecordStatus());
                country.setCreateDate(savedEarlierCountry.getCreateDate());
                country.setModifyDate(now);
                return countryDAO.save(country);
            }
            return null;
        }
        return null;
    }
    
    public boolean noCountryWithSameName(Country country) {
        Integer thisId = country.getId();
        Country countryWithSameName = countryDAO.getFirstByNameTrimAndCaseIgnore(country.getName());
        if (countryWithSameName == null) return true;
        if (thisId == countryWithSameName.getId()) return true;
        return false;
    }

    public List<Country> getAll(Long modifyDate) {
        if (modifyDate == null) modifyDate = 0L;
        return countryDAO.getAll(modifyDate);
    }
    
    public void deleteById (Integer id) {
        countryDAO.deleteById(id);
    }

}
