package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.model.FavoriteEvent;
import ua.i.mail100.service.FavoriteEventService;

import java.util.List;

@RestController
@RequestMapping("favorite-event")
@Slf4j
@Profile("rest")
public class FavoriteEventController {
    @Autowired
    FavoriteEventService favoriteEventService;

    @Autowired
    MapperFavoriteEventUtil mapperFavoriteEventUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String requestBody) {
        FavoriteEventDTO favoriteEventDTO = mapperFavoriteEventUtil.toDTO(requestBody);
        FavoriteEvent favoriteEvent = mapperFavoriteEventUtil.toObject(favoriteEventDTO);
        FavoriteEvent savedFavoriteEvent = favoriteEventService.save(favoriteEvent);
        if (savedFavoriteEvent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        FavoriteEventDTO resultDTO = mapperFavoriteEventUtil.toDTO(savedFavoriteEvent);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody String requestBody) {
        FavoriteEventDTO favoriteEventDTO = mapperFavoriteEventUtil.toDTO(requestBody);
        FavoriteEvent favoriteEvent = mapperFavoriteEventUtil.toObject(favoriteEventDTO);
        FavoriteEvent updatedFavoriteEvent = favoriteEventService.update(favoriteEvent);
        if (updatedFavoriteEvent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        FavoriteEventDTO resultDTO = mapperFavoriteEventUtil.toDTO(updatedFavoriteEvent);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getFavoriteEvent(@PathVariable(required = false) Integer id) {
        if (id != null) {
            FavoriteEvent favoriteEvent = favoriteEventService.getById(id);
            if (favoriteEvent == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            FavoriteEventDTO resultDTO = mapperFavoriteEventUtil.toDTO(favoriteEvent);
            return new ResponseEntity(resultDTO, HttpStatus.OK);
        } else {
            List<FavoriteEvent> favoriteEvents = favoriteEventService.getAll();
            List<FavoriteEventDTO> favoriteEventDTOS = mapperFavoriteEventUtil.toDTOList(favoriteEvents);
            return new ResponseEntity(favoriteEventDTOS, HttpStatus.OK);
        }
    }
}
