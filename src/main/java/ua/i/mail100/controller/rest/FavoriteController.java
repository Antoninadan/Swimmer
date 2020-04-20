package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.dto.FavoriteDTO;
import ua.i.mail100.mapper.MapperFavoriteUtil;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.Favorite;
import ua.i.mail100.model.User;
import ua.i.mail100.service.EventService;
import ua.i.mail100.service.FavoriteService;
import ua.i.mail100.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("favorite")
@Slf4j
@Profile("rest")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    MapperFavoriteUtil mapperFavoriteUtil;

    @PutMapping("save")
    public ResponseEntity save(@RequestBody String requestBody) {
        FavoriteDTO favoriteDTO = mapperFavoriteUtil.toDTO(requestBody);
        Favorite favorite = mapperFavoriteUtil.toObject(favoriteDTO);
        if (!favoriteService.isFavoriteComplete(favorite))
            return new ResponseEntity("not complete favorite data", HttpStatus.BAD_REQUEST);
        User user = userService.getById(favorite.getUser().getId());
        if (user == null)
            return new ResponseEntity("user not found", HttpStatus.BAD_REQUEST);
        if (!userService.isUserAvailability(user))
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);
        Event event = favorite.getEvent();
        if (!eventService.isEventAvailable(event))
            return new ResponseEntity("event is deleted", HttpStatus.BAD_REQUEST);

        Favorite savedFavorite = favoriteService.save(favorite);
        if (savedFavorite == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        FavoriteDTO resultDTO = mapperFavoriteUtil.toDTO(savedFavorite);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping("soft-delete")
    public ResponseEntity softDelete(@RequestBody String requestBody) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(requestBody);
        Integer id = (Integer) (map.get("id"));
        Favorite favorite = favoriteService.getById(id);
        if (favorite == null) {
            return new ResponseEntity("record not found", HttpStatus.NOT_FOUND);
        }

        Favorite softDeletedFavorite = favoriteService.softDelete(favorite);
        if (softDeletedFavorite == null) {
            return new ResponseEntity("record not soft deleted", HttpStatus.BAD_REQUEST);
        }
        FavoriteDTO favoriteDTO = mapperFavoriteUtil.toDTO(softDeletedFavorite);
        return new ResponseEntity(favoriteDTO, HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity getFavotite(@PathVariable Integer id) {
        Favorite favorite = favoriteService.getById(id);
        if (favorite == null) {
            return new ResponseEntity("record not found", HttpStatus.NOT_FOUND);
        }
        FavoriteDTO favoriteDTO = mapperFavoriteUtil.toDTO(favorite);
        return new ResponseEntity(favoriteDTO, HttpStatus.OK);
    }

    @GetMapping({"all/", "all/{modifyDate}"})
    public ResponseEntity getAllModifiedAfter(@PathVariable(required = false) Long modifyDate, Integer userId) {
        List<Favorite> favorites = favoriteService.getAllByUserAndModifiedAfter(userId, modifyDate);
        List<FavoriteDTO> favoriteDTOS = mapperFavoriteUtil.toDTOList(favorites);
        return new ResponseEntity(favoriteDTOS, HttpStatus.OK);
    }
}
