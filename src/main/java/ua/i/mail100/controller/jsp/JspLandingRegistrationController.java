package ua.i.mail100.controller.jsp;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.i.mail100.model.Sex;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class JspLandingRegistrationController {

    @GetMapping()
    public String getLandingPage(Model model) {
        List sexList = Arrays.asList(Sex.values());
        model.addAttribute("sexList", sexList);
        return "registration";
    }
}
