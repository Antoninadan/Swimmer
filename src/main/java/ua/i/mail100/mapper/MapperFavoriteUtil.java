package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.FavoriteDTO;
import ua.i.mail100.model.*;
import ua.i.mail100.presenter.FavoritePresenter;
import ua.i.mail100.presenter.ResultPresenter;
import ua.i.mail100.service.EventService;
import ua.i.mail100.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MapperFavoriteUtil {
    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public Favorite toObject(FavoriteDTO favoriteDTO) {
        Favorite favorite = new Favorite();
        favorite.setId(favoriteDTO.getId());
        favorite.setEvent(favoriteDTO.getEventId() != null ?
                eventService.getById(favoriteDTO.getEventId()) : null);
        favorite.setUser(favoriteDTO.getUserId() != null ?
                userService.getById(favoriteDTO.getUserId()) : null);
        return favorite;
    }
    
    public FavoriteDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, FavoriteDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FavoriteDTO toDTO(Favorite favorite) {
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setEventId(favorite.getEvent() != null ?
                favorite.getEvent().getId() : null);
        favoriteDTO.setUserId(favorite.getUser() != null ?
                favorite.getUser().getId() : null);
        favoriteDTO.setCreateDate(favorite.getCreateDate());
        favoriteDTO.setModifyDate(favorite.getModifyDate());
        favoriteDTO.setRecordStatus(favorite.getRecordStatus().toString());
        return favoriteDTO;
    }

    public List<FavoriteDTO> toDTOList(List<Favorite> favorites) {
        Collections.sort(favorites);
        List<FavoriteDTO> favoriteDTOS = new ArrayList<>();
        for (Favorite each : favorites) {
            FavoriteDTO favoriteDTO = toDTO(each);
            favoriteDTOS.add(favoriteDTO);
        }
        return favoriteDTOS;
    }

    public FavoritePresenter toPresenter(Favorite favorite) {
        FavoritePresenter favoritePresenter = new FavoritePresenter();
                Event event = favorite.getEvent();
        String eventStr = "id=" + Integer.toString(event.getId()) +
                (event.getFranchise() != null ? " " + event.getFranchise().getName() : "") +
                (event.getName() != null ? " name=" + event.getName() : "");
        User user = favorite.getUser();
        String userStr = "id=" + Integer.toString(user.getId());

        favoritePresenter.setId(favorite.getId());
        favoritePresenter.setEvent(eventStr);
        favoritePresenter.setUser(userStr);
        favoritePresenter.setCreateDate(favorite.getCreateDate());
        favoritePresenter.setModifyDate(favorite.getModifyDate());
        favoritePresenter.setRecordStatus(favorite.getRecordStatus().toString());
        return favoritePresenter;
    }

    public List<FavoritePresenter> toFavoritePresenterList(List<Favorite> favorites) {
        Collections.sort(favorites);
        List<FavoritePresenter> favoritePresenters = new ArrayList<>();
        for (Favorite each : favorites) {
            FavoritePresenter favoritePresenter = toPresenter(each);
            favoritePresenters.add(favoritePresenter);
        }
        return favoritePresenters;
    }
}
