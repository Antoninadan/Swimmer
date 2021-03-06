package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.UserDTO;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.User;
import ua.i.mail100.model.UserStatus;
import ua.i.mail100.service.UserCodeService;
import ua.i.mail100.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("direct-user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserCodeService userCodeService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @PutMapping("save")
    public ResponseEntity save(@RequestBody String requestBody) {
        UserDTO userDTO = mapperUserUtil.toDTO(requestBody);
        User user = mapperUserUtil.toObject(userDTO);
        User savedUser = userService.save(user);
        if (savedUser == null) {
            return new ResponseEntity("user not saved", HttpStatus.BAD_REQUEST);
        }
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(savedUser);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }


    @PostMapping("update")
    public ResponseEntity update(@RequestBody String requestBody) {
        UserDTO userDTO = mapperUserUtil.toDTO(requestBody);
        User user = mapperUserUtil.toObjectForUpdate(userDTO);
        if(!userService.isUserExists(user))
            return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);
        if (!userService.isUserAvailability(user))
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);
        User updatedUser = userService.update(user);
        if (updatedUser == null)
            return new ResponseEntity("user not updated", HttpStatus.BAD_REQUEST);
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(updatedUser);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping("update-password")
    public ResponseEntity updatePassword(@RequestBody String requestBody) {
        UserDTO userDTO = mapperUserUtil.toDTO(requestBody);
        User user = mapperUserUtil.toObjectForUpdatePassword(userDTO);
        if(!userService.isUserExists(user))
            return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);
        if (!userService.isUserAvailability(user))
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);
        User updatedUser = userService.update(user);
        if (updatedUser == null)
            return new ResponseEntity("user not updated", HttpStatus.BAD_REQUEST);
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(updatedUser);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity getUser(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity("user not found", HttpStatus.NOT_FOUND);
        }
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(user);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping("auth")
    public ResponseEntity getByLoginAndPassword(@RequestBody String body) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(body);
        User user = userService.getByLoginAndPassword((String) map.get("login"), (String) map.get("password"));
        if (user == null) {
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);
        }
        UserSecurityDTO resultDTO = mapperUserUtil.toDTO(user);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping("unique/{login}")
    public ResponseEntity checkUniqueLogin(@PathVariable String login) {
        return new ResponseEntity(userService.noUserWithSameLogin(login), HttpStatus.OK);
    }

    @GetMapping("send-code/{id}")
    public ResponseEntity sendCode(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity("user not available", HttpStatus.NOT_FOUND);
        }
        userCodeService.saveCodeAndSendMailWithCode(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("check-mail/{id}/{code}")
    public ResponseEntity sendCode(@PathVariable("id") Integer id, @PathVariable("code") String code) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity("user not available", HttpStatus.NOT_FOUND);
        }
        boolean result = userCodeService.checkCode(user, code);
        if (result) {
            userService.updateUserStatus(user, UserStatus.ACTIVE);
        }
        return new ResponseEntity(userCodeService.checkCode(user, code), HttpStatus.OK);
    }

    @GetMapping("recovery-password/{login}")
    public ResponseEntity recoveryPassword(@PathVariable("login") String login) {
        User user = userService.getByLogin(login);
        if (user == null) {
            return new ResponseEntity("user not available",  HttpStatus.NOT_FOUND);
        }
        if (!userService.isUserAvailability(user)){
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);
        }

        User updatedUser = userService.saveRecoveryPasswordAndSendMail(user);
        if (updatedUser == null) {
            return new ResponseEntity("user not updated", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

// todo role?
// todo secirity all requests?