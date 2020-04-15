package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.CountryDTO;
import ua.i.mail100.model.*;
import ua.i.mail100.service.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class MapperCountryUtil {
    @Autowired
    ObjectMapper objectMapper;

    public Country toObject(CountryDTO countryDTO) {
        Country country = new Country();
        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        return country;
    }

    public CountryDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, CountryDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CountryDTO toDTO(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        countryDTO.setCreateDate(country.getCreateDate());
        countryDTO.setModifyDate(country.getModifyDate());
        countryDTO.setRecordStatus(country.getRecordStatus().toString());
        return countryDTO;
    }

    public List<CountryDTO> toDTOList(List<Country> countries) {
        Collections.sort(countries);

        List<CountryDTO> countryDTOs = new ArrayList<>();
        for (Country each : countries) {
            CountryDTO countryDTO = toDTO(each);
            countryDTOs.add(countryDTO);
        }
        return countryDTOs;
    }
}
