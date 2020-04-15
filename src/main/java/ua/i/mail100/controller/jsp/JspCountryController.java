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
import ua.i.mail100.mapper.MapperCountryUtil;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.Country;
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

    @GetMapping("open-for-save")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        return "country-save";
    }

    @PostMapping("update")
    public String update(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "countryId") String countryId,
                         @RequestParam(value = "name") String name) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!countryCheckAndMake(model, countryId)) return "countries";

        Country country = countryService.getById(Integer.valueOf(countryId));
        country.setName(name);
        if (!countryService.noCountryWithSameName(country)) {
            model.addAttribute("country", country);
            model.addAttribute("message", "Name not unique!");
            return "country-edit";
        }

        Country updatedCountry = countryService.update(country);
        if (!countryCheckAndMake(model, updatedCountry)) return "country-edit";
        countriesMake(model);
        return "countries";
    }

    @PostMapping("save")
    public String save(Model model,
                       @RequestParam(value = "userId") String userId,
                       @RequestParam(value = "name") String name) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";

        Country country = new Country(null, name, null, null, null);
        if (!countryService.noCountryWithSameName(country)) {
            model.addAttribute("message", "Name not unique!");
            return "country-save";
        }

        Country savedCountry = countryService.save(country);
        if (!countryCheckAndMake(model, savedCountry)) return "country-edit";
        countriesMake(model);
        return "countries";
    }

    @PostMapping("delete")
    public String delete(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "countryId") String countryId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!countryCheckAndMake(model, countryId)) return "countries";

        try {
            countryService.deleteById(Integer.valueOf(countryId));
        } catch (Exception e) {
            model.addAttribute("message", "Record cannot be deleted!");
            e.printStackTrace();
            return "country-edit";
        }
        countriesMake(model);
        return "countries";
    }

    public boolean countryCheckAndMake(Model model, String countryId) {
        Integer countryIdSelected = Integer.valueOf(countryId);
        Country country = countryService.getById(countryIdSelected);
        if (country != null) {
            CountryDTO countryDTO = mapperCountryUtil.toDTO(country);
            model.addAttribute("country", countryDTO);
            return true;
        } else {
            model.addAttribute("message", "Record are wrong!");
            return false;
        }
    }

    public boolean countryCheckAndMake(Model model, Country country) {
        if (country != null) {
            CountryDTO countryDTO = mapperCountryUtil.toDTO(country);
            model.addAttribute("country", countryDTO);
            return true;
        } else {
            model.addAttribute("message", "Record are wrong!");
            return false;
        }
    }

    public void countriesMake(Model model) {
        List<Country> countries = countryService.getAll(0L);
        List<CountryDTO> countryDTOS = mapperCountryUtil.toDTOList(countries);
        model.addAttribute("countries", countryDTOS);
    }
}

// TODO normal date control
// TODO check login unique