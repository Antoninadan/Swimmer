package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.Sex;
import ua.i.mail100.model.User;
import ua.i.mail100.service.DateService;
import ua.i.mail100.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("user")
public class JspUserController {
    @Autowired
    UserService userService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @Autowired
    DateService dateService;


    @PostMapping("auth")
    public String getAuthUser(Model model,
                              @RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password) {
        User user = userService.getByLoginAndPassword(login, password);
        if (user == null) {
            model.addAttribute("message", "Login or password are wrong!");
            return "authorization";
        }
        UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
        model.addAttribute("user", userSecurityDTO);

        return "user-cabinet";
    }

    @PostMapping("save")
    public String save(Model model,
                       @RequestParam(value = "login") String login,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "sex") String sex,
                       @RequestParam(value = "birth_date") String birthDate) {
        User inputedUser = new User(null, login, password, name, Sex.valueOf(sex), dateService.parse(birthDate),
                null, null, null, null);
        User user = userService.save(inputedUser);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("userId", user.getId());
            return "user-cabinet";
        } else {
            model.addAttribute("message", "Sorry, but user does not saved");
            return "registration";
        }
    }
}

// TODO normal date control
// TODO check login unique