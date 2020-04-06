package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.CountryDTO;
import ua.i.mail100.mapper.MapperCountryUtil;
import ua.i.mail100.model.Country;
import ua.i.mail100.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("country")
@Slf4j
@Profile("rest")
public class CountryController {
    @Autowired
    CountryService countryService;

    @Autowired
    MapperCountryUtil mapperCountryUtil;

    @PutMapping("save")
    public ResponseEntity save(@RequestBody Country country) {
        Country savedCountry = countryService.save(country);
        if (savedCountry == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(country, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Country country) {
        Country updatedUser = countryService.update(country);
        if (updatedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(country, HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity getCountry(@PathVariable Integer id) {
        Country country = countryService.getById(id);
        if (country == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(country, HttpStatus.OK);
    }

    @GetMapping({"all/", "all/{modifyDate}"})
    public ResponseEntity getAll(@PathVariable(required = false) Long modifyDate) {
        List<Country> countrys = countryService.getAll(modifyDate);
        List<CountryDTO> countryDTOS = mapperCountryUtil.toDTOList(countrys);
        return new ResponseEntity(countryDTOS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            countryService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Wrong id = %d", id));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
