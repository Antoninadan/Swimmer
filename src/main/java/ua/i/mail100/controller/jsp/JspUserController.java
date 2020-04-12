package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.User;
import ua.i.mail100.service.UserService;

import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("user")
public class JspUserController {
    @Autowired
    UserService userService;

    @Autowired
    MapperUserUtil mapperUserUtil;


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
}
