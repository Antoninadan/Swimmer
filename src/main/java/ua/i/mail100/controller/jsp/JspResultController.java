package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.i.mail100.dto.*;
import ua.i.mail100.mapper.*;
import ua.i.mail100.model.*;
import ua.i.mail100.presenter.EventPresenter;
import ua.i.mail100.presenter.FavoritePresenter;
import ua.i.mail100.presenter.ResultPresenter;
import ua.i.mail100.service.*;

import java.util.Date;
import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("result")
public class JspResultController {
    @Autowired
    UserService userService;

    @Autowired
    DistanceService distanceService;

    @Autowired
    ResultService resultService;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    JspService jspService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @Autowired
    MapperResultUtil mapperResultUtil;

    @Autowired
    MapperFavoriteUtil mapperFavoriteUtil;

    @Autowired
    DateService dateService;

    @GetMapping("open")
    public String openEvents(Model model,
                             @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        return "results";
    }

    @GetMapping("get-swimmer")
    public String openEvents(Model model,
                             @RequestParam(value = "userId") String userId,
                             @RequestParam(value = "swimmerId") String swimmerId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!swimmerCheckAndMake(model, swimmerId)) return "results";

        List<Result> results = resultService.
                getAllByUserAndDistanceAndModifiedAfter(Integer.valueOf(swimmerId),
                        null, 0L);
        List<ResultPresenter> resultPresenters = mapperResultUtil.toResultPresenterList(results);
        model.addAttribute("results", resultPresenters);


        List<Favorite> favorites = favoriteService.
                getAllByUserAndModifiedAfter(Integer.valueOf(swimmerId),0L);
        List<FavoritePresenter> favoritePresenters = mapperFavoriteUtil.toFavoritePresenterList(favorites);
        model.addAttribute("favorites", favoritePresenters);
        return "results";
    }

    public boolean swimmerCheckAndMake(Model model, String swimmerId) {
        Integer swimmerIdSelected = Integer.valueOf(swimmerId);
        User user = userService.getById(swimmerIdSelected);
        if (user != null) {
            UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
            model.addAttribute("swimmer", userSecurityDTO);
            return true;
        } else {
            model.addAttribute("message", "User not found!");
            return false;
        }
    }
}