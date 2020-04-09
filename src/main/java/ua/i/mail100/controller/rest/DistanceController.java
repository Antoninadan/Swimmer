package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.DistanceDTO;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.mapper.MapperDistanceUtil;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.service.DistanceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("distance")
@Slf4j
@Profile("rest")
public class DistanceController {
    @Autowired
    DistanceService distanceService;

    @Autowired
    MapperDistanceUtil mapperDistanceUtil;

    @PutMapping("save")
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
    
    @PostMapping("soft-delete")
    public ResponseEntity softDelete(@RequestBody String requestBody) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(requestBody);
        Integer id = (Integer) (map.get("id"));
        Distance distance = distanceService.getById(id);
        if (distance == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        distance.setRecordStatus(RecordStatus.DELETED);
        Distance updatedDistance = distanceService.update(distance);
        if (updatedDistance == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        DistanceDTO resultDTO = mapperDistanceUtil.toDTO(updatedDistance);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping("update")
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

    @GetMapping("by-id/{id}")
    public ResponseEntity getDistance(@PathVariable Integer id) {
        Distance distance = distanceService.getById(id);
        if (distance == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        DistanceDTO resultDTO = mapperDistanceUtil.toDTO(distance);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity getAll(@PathVariable Long modifyDate) {
        List<Distance> distances = distanceService.getAll();
        List<DistanceDTO> distanceDTOS = mapperDistanceUtil.toDTOList(distances);
        return new ResponseEntity(distanceDTOS, HttpStatus.OK);
    }
    
    
}
