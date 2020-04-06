package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.CountryDTO;
import ua.i.mail100.dto.UserDTO;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Sex;
import ua.i.mail100.model.User;
import ua.i.mail100.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperUserUtil {
    @Autowired
    ObjectMapper objectMapper;

    public User toObject(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setSex(userDTO.getSex() != null ?
                Sex.valueOf(userDTO.getSex()) : null);
        user.setBirthDate(userDTO.getBirthDate());
        user.setUserStatus(userDTO.getUserStatus());
        return country;
    }

    public UserDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public List<CountryDTO> toDTOList(List<Country> countrys) {
        List<CountryDTO> orderDTOs = new ArrayList<>();
        for (Country each : countrys) {
            CountryDTO countryDTO = toDTO(each);
            orderDTOs.add(countryDTO);
        }
        return orderDTOs;
    }
}
