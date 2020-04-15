package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.i.mail100.dto.CountryDTO;
import ua.i.mail100.dto.UserSecurityDTO;
import ua.i.mail100.mapper.MapperCountryUtil;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Sex;
import ua.i.mail100.model.User;
import ua.i.mail100.service.CountryService;
import ua.i.mail100.service.DateService;
import ua.i.mail100.service.UserService;

import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("country")
public class JspCountryController {
    @Autowired
    UserService userService;

    @Autowired
    CountryService countryService;

    @Autowired
    JspService jspService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @Autowired
    MapperCountryUtil mapperCountryUtil;

    @Autowired
    DateService dateService;

    @GetMapping("open-all")
    public String openCountries(Model model,
                                @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        countriesMake(model);
        return "countries";
    }

    @GetMapping("open-for-edit")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId,
                              @RequestParam(value = "countryId") String countryId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!countryCheckAndMake(model, countryId)) return "countries";
        return "country-edit";
    }

    @PostMapping("edit")
    public String edit(Model model,
                       @RequestParam(value = "userId") String userId,
                       @RequestParam(value = "countryId") String countryId,
                       @RequestParam(value = "name") String name) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!countryCheckAndMake(model, countryId)) return "countries";
        Country country = countryService.getById(Integer.valueOf(countryId));
        country.setName(name);
        if (!countryService.noCountryWithSameName(country)) {
            model.addAttribute("message", "Country name not unique!");
            return "country-edit";
        }
        Country updatedCountry = countryService.update(country);
        if (updatedCountry != null) {
            countriesMake(model);
            return "countries";
        } else {
            model.addAttribute("message", "Country not saved!");
            return "country-edit";
        }
    }

    public boolean countryCheckAndMake(Model model, String countryId) {
        Integer countryIdSelected = Integer.valueOf(countryId);
        Country country = countryService.getById(countryIdSelected);
        if (country != null) {
            CountryDTO countryDTO = mapperCountryUtil.toDTO(country);
            model.addAttribute("country", countryDTO);
            return true;
        } else {
            model.addAttribute("message", "Country are wrong!");
            return false;
        }
    }

    public void countriesMake(Model model) {
        List<Country> countries = countryService.getAll(0L);
        List<CountryDTO> countryDTOS = mapperCountryUtil.toDTOList(countries);
        model.addAttribute("countries", countryDTOS);
    }

//    @PostMapping("edit")
//    public String edit(Model model,
//                       @RequestParam(value = "userId") String userId,
//                       @RequestParam(value = "countryId") String countryId,
//                       @RequestParam(value = "name") String name) {
//        if (jspService.userCheckAndMake(model, userId)) {
//            if (countryCheckAndMake(model, countryId)) {
//                Country country = countryService.getById(Integer.valueOf(countryId));
//                country.setName(name);
//                if (countryService.noCountryWithSameName(country)) {
//                    Country updatedCountry = countryService.update(country);
//                    if (updatedCountry != null) {
//                        countriesMake(model);
//                        return "countries";
//                    } else {
//                        model.addAttribute("message", "Country not saved!");
//                        return "country-edit";
//                    }
//                    return "country-edit";
//                }
//            } else
//                return "countries";
//        } else
//            return "authorization";
//    }


//        Integer userIdSelected = Integer.valueOf(userId);
//        Integer countryIdSelected = Integer.valueOf(countryId);
//        User user = userService.getIfUserExistsAndAvailable(userIdSelected);
//        if (user != null) {
//            Country country = countryService.getById(countryIdSelected);
//            if (country != null) {
//                country.setName(name);
//                if (countryService.noCountryWithSameName(country)) {
//                    Country updatedCountry = countryService.update(country);
//                    if (updatedCountry != null) {
//                        UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
//                        model.addAttribute("user", userSecurityDTO);
//
//                        countriesMake(model);
//                        return "countries";
//                    } else {
//                        UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
//                        model.addAttribute("user", userSecurityDTO);
//
//                        CountryDTO countryDTO = mapperCountryUtil.toDTO(country);
//                        model.addAttribute("country", countryDTO);
//
//                        model.addAttribute("message", "Country not saved!");
//                        return "country-edit";
//                    }
//                } else {
//                    UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
//                    model.addAttribute("user", userSecurityDTO);
//
//                    CountryDTO countryDTO = mapperCountryUtil.toDTO(country);
//                    model.addAttribute("country", countryDTO);
//
//                    model.addAttribute("message", "Country name not unique!");
//                    return "country-edit";
//                }
//            } else {
//                UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
//                model.addAttribute("user", userSecurityDTO);
//
//                countriesMake(model);
//
//                model.addAttribute("message", "Country are wrong!");
//            }
//            return "countries";
//        } else {
//            model.addAttribute("message", "User are wrong!");
//            return "authorization";
//        }


//    @PostMapping("auth")
//    public String getAuthUser(Model model,
//                              @RequestParam(value = "login") String login,
//                              @RequestParam(value = "password") String password) {
//        User user = userService.getByLoginAndPassword(login, password);
//        if (user != null) {
//            UserSecurityDTO userSecurityDTO = mapperUserUtil.toDTO(user);
//            model.addAttribute("user", userSecurityDTO);
//            return "admin-cabinet";
//        } else {
//            model.addAttribute("message", "Login or password are wrong!");
//            return "authorization";
//        }
//    }
//
}

// TODO normal date control
// TODO check login unique