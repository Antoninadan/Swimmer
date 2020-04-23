package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.FranchiseDTO;
import ua.i.mail100.mapper.MapperFranchiseUtil;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.service.FranchiseService;

import java.util.List;

@RestController
@RequestMapping("franchise")
@Slf4j
@Profile("rest")
public class FranchiseController {
    @Autowired
    FranchiseService franchiseService;

    @Autowired
    MapperFranchiseUtil mapperFranchiseUtil;

    @PutMapping("save")
    public ResponseEntity save(@RequestBody Franchise franchise) {
        Franchise savedFranchise = franchiseService.save(franchise);
        if (savedFranchise == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(franchise, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Franchise franchise) {
        Franchise updatedFranchise = franchiseService.update(franchise);
        if (updatedFranchise == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(franchise, HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity getFranchise(@PathVariable Integer id) {
        Franchise franchise = franchiseService.getById(id);
        if (franchise == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(franchise, HttpStatus.OK);
    }
    
    @GetMapping({"all/", "all/{modifyDate}"})
    public ResponseEntity getAll(@PathVariable(required = false) Long modifyDate) {
        List<Franchise> franchises = franchiseService.getAll(modifyDate);
        List<FranchiseDTO> franchiseDTOS = mapperFranchiseUtil.toDTOList(franchises);
        return new ResponseEntity(franchiseDTOS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            franchiseService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Wrong id = %d", id));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}