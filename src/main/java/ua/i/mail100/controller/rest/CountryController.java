package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.model.Country;
import ua.i.mail100.service.CountryService;

import java.util.Map;

@RestController
@RequestMapping("country")
@Slf4j
@Profile("rest")
public class CountryController {
    @Autowired
    CountryService countryService;

    @PutMapping
    public ResponseEntity save(@RequestBody Country country) {
        Country savedCountry = countryService.save(country);
        if (savedCountry == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(country, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Country country) {
        Country updatedUser = countryService.update(country);
        if (updatedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(country, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable Integer id) {
        Country country = countryService.getById(id);
        if (country == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(country, HttpStatus.OK);
    }
}
