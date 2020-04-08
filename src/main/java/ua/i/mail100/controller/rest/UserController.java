package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.dto.UserDTO;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.User;
import ua.i.mail100.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
@Profile("rest")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @PutMapping("save")
    public ResponseEntity save(@RequestBody String requestBody) {
        UserDTO userDTO = mapperUserUtil.toDTO(requestBody);
        User user = mapperUserUtil.toObject(userDTO);
        User savedUser = userService.save(user);
        if (savedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(savedUser);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }


    @PostMapping("update")
    public ResponseEntity update(@RequestBody String requestBody) {
        UserDTO userDTO = mapperUserUtil.toDTO(requestBody);
        User user = mapperUserUtil.toObject(userDTO);
        User updatedUser = userService.update(user);
        if (updatedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(updatedUser);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity getEvent(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(user);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    // todo
    @PostMapping("auth")
    public ResponseEntity getByLoginAndPassword(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        User user = userService.getByLoginAndPassword((String) map.get("login"), (String) map.get("password"));
        if (user == null) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("unique/{login}")
    public ResponseEntity checkUniqueLogin(@PathVariable String login) {
        return new ResponseEntity(userService.noUserWithSameLogin(login), HttpStatus.OK);
    }

}

// todo role?
// todo check email
// todo check unique