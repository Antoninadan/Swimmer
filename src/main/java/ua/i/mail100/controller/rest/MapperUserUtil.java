package ua.i.mail100.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.service.UserService;

import java.io.IOException;

@Component
public class MapperUserUtil {
    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public UserDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
