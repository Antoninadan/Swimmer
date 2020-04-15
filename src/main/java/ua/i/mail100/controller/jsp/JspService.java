package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.mapper.MapperCountryUtil;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.User;
import ua.i.mail100.service.CountryService;
import ua.i.mail100.service.DateService;
import ua.i.mail100.service.UserService;

@Service
public class JspService {
    @Autowired
    UserService userService;

    @Autowired
    MapperUserUtil mapperUserUtil;


    public boolean userCheckAndMake(Model model, String userId){
        Integer userIdSelected = Integer.valueOf(userId);
        User user = userService.getIfUserExistsAndAvailable(userIdSelected);
        if (user != null) {
            UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
            model.addAttribute("user", userSecurityDTO);
            return true;

        } else {
            model.addAttribute("message", "User are wrong!");
            return false;
        }
    }
}
