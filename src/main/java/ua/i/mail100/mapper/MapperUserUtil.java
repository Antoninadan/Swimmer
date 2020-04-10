package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.UserDTO;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.model.Sex;
import ua.i.mail100.model.User;
import ua.i.mail100.service.DateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperUserUtil {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DateService dateService;

    public User toObject(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setSex(userDTO.getSex() != null ?
                Sex.valueOf(userDTO.getSex()) : null);
        user.setBirthDate(dateService.parse(userDTO.getBirthDate()));
        return user;
    }

    public UserDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserSecurityDTO toDTO(User user) {
        UserSecurityDTO userSecurityDTO = new UserSecurityDTO();
        userSecurityDTO.setId(user.getId());
        userSecurityDTO.setLogin(user.getLogin());
        userSecurityDTO.setName(user.getName());
        userSecurityDTO.setSex(user.getSex() != null ?
                user.getSex().toString() : null);
        userSecurityDTO.setBirthDate(dateService.toString(user.getBirthDate()));
        userSecurityDTO.setCreateDate(user.getCreateDate());
        userSecurityDTO.setModifyDate(user.getModifyDate());
        userSecurityDTO.setRecordStatus(user.getRecordStatus().toString());
        return userSecurityDTO;
    }

    public List<UserSecurityDTO> toDTOList(List<User> users) {
        List<UserSecurityDTO> userSecurityDTOs = new ArrayList<>();
        for (User each : users) {
            UserSecurityDTO userSecurityDTO = toDTO(each);
            userSecurityDTOs.add(userSecurityDTO);
        }
        return userSecurityDTOs;
    }
}
