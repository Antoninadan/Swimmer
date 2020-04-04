package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.model.Distance;
import ua.i.mail100.service.DistanceService;

import java.util.List;

@RestController
@RequestMapping("distance")
@Slf4j
@Profile("rest")
public class DistanceController {
    @Autowired
    DistanceService distanceService;

    @Autowired
    MapperDistanceUtil mapperDistanceUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String requestBody) {
        DistanceDTO distanceDTO = mapperDistanceUtil.toDTO(requestBody);
        Distance distance = mapperDistanceUtil.toObject(distanceDTO);
        Distance savedDistance = distanceService.save(distance);
        if (savedDistance == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        DistanceDTO resultDTO = mapperDistanceUtil.toDTO(savedDistance);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody String requestBody) {
        DistanceDTO distanceDTO = mapperDistanceUtil.toDTO(requestBody);
        Distance distance = mapperDistanceUtil.toObject(distanceDTO);
        Distance updatedDistance = distanceService.update(distance);
        if (updatedDistance == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        DistanceDTO resultDTO = mapperDistanceUtil.toDTO(updatedDistance);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getDistance(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Distance distance = distanceService.getById(id);
            if (distance == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            DistanceDTO resultDTO = mapperDistanceUtil.toDTO(distance);
            return new ResponseEntity(resultDTO, HttpStatus.OK);
        } else {
            List<Distance> distances = distanceService.getAll();
            List<DistanceDTO> distanceDTOS = mapperDistanceUtil.toDTOList(distances);
            return new ResponseEntity(distanceDTOS, HttpStatus.OK);
        }
    }
}
