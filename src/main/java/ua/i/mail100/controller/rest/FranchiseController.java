package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.service.FileService;
import ua.i.mail100.service.FranchiseService;

@RestController
@RequestMapping("franchise")
@Slf4j
@Profile("rest")
public class FranchiseController {
    @Autowired
    FranchiseService franchiseService;

    @PutMapping
    public ResponseEntity save(@RequestBody Franchise franchise) {
        Franchise savedCountry = franchiseService.save(franchise);
        if (savedCountry == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(franchise, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Franchise franchise) {
        Franchise updatedUser = franchiseService.update(franchise);
        if (updatedUser == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(franchise, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity getFranchise(@PathVariable Integer id) {
        Franchise franchise = franchiseService.getById(id);
        if (franchise == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(franchise, HttpStatus.OK);
    }
}