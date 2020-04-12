package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.UserDTO;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.model.Sex;
import ua.i.mail100.model.User;
import ua.i.mail100.service.DateService;
import ua.i.mail100.service.UserService;
import ua.i.mail100.util.EncodeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperUserUtil {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DateService dateService;

    @Autowired
    UserService userService;

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

    public User toObjectForUpdate(UserDTO userDTO) {
        User user = new User();
        Integer id = userDTO.getId();
        user.setId(id);
        user.setName(userDTO.getName());
        user.setSex(userDTO.getSex() != null ?
                Sex.valueOf(userDTO.getSex()) : null);
        user.setBirthDate(dateService.parse(userDTO.getBirthDate()));
        if(!userService.isUserExists(user)) return null;

        User savedEarlierUser = userService.getById(id);
        user.setLogin(savedEarlierUser.getLogin());
        user.setPassword(savedEarlierUser.getPassword());
        user.setUserStatus(savedEarlierUser.getUserStatus());
        user.setRecordStatus(savedEarlierUser.getRecordStatus());
        user.setModifyDate(savedEarlierUser.getModifyDate());
        user.setCreateDate(savedEarlierUser.getCreateDate());
        return user;
    }

    public User toObjectForUpdatePassword(UserDTO userDTO) {
        User user = new User();
        Integer id = userDTO.getId();
        user.setId(id);
        String password = userDTO.getPassword();
        user.setPassword(EncodeUtil.encode(password));
        if(!userService.isUserExists(user)) return null;

        User savedEarlierUser = userService.getById(id);
        user.setName(savedEarlierUser.getName());
        user.setLogin(savedEarlierUser.getLogin());
        user.setSex(savedEarlierUser.getSex());
        user.setBirthDate(savedEarlierUser.getBirthDate());
        user.setUserStatus(savedEarlierUser.getUserStatus());
        user.setRecordStatus(savedEarlierUser.getRecordStatus());
        user.setModifyDate(savedEarlierUser.getModifyDate());
        user.setCreateDate(savedEarlierUser.getCreateDate());
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
