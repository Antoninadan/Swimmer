package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.FavoriteEventDTO;
import ua.i.mail100.model.FavoriteEvent;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.service.EventService;
import ua.i.mail100.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MapperFavoriteEventUtil {
    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public FavoriteEvent toObject(FavoriteEventDTO favoriteEventDTO) {
        FavoriteEvent favoriteEvent = new FavoriteEvent();
        favoriteEvent.setId(favoriteEventDTO.getId());
        favoriteEvent.setEvent(favoriteEventDTO.getEventId() != null ?
                eventService.getById(favoriteEventDTO.getEventId()) : null);
        favoriteEvent.setUser(favoriteEventDTO.getUserId() != null ?
                userService.getById(favoriteEventDTO.getUserId()) : null);
        favoriteEvent.setCreateDate(favoriteEventDTO.getCreateDate());
        favoriteEvent.setModifyDate(favoriteEventDTO.getModifyDate());
        favoriteEvent.setRecordStatus(RecordStatus.valueOf(favoriteEventDTO.getRecordStatus()));
        return favoriteEvent;
    }

    public FavoriteEventDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, FavoriteEventDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FavoriteEventDTO toDTO(FavoriteEvent favoriteEvent) {
        FavoriteEventDTO favoriteEventDTO = new FavoriteEventDTO();
        favoriteEventDTO.setId(favoriteEvent.getId());
        favoriteEventDTO.setEventId(favoriteEvent.getEvent() != null ?
                favoriteEvent.getEvent().getId() : null);
        favoriteEventDTO.setUserId(favoriteEvent.getUser() != null ?
                favoriteEvent.getUser().getId() : null);
        favoriteEventDTO.setCreateDate(favoriteEvent.getCreateDate());
        favoriteEventDTO.setModifyDate(favoriteEvent.getModifyDate());
        favoriteEventDTO.setRecordStatus(favoriteEvent.getRecordStatus().toString());
        return favoriteEventDTO;
    }

    public List<FavoriteEventDTO> toDTOList(List<FavoriteEvent> favoriteEvents) {
        Collections.sort(favoriteEvents);
        List<FavoriteEventDTO> favoriteEventDTOs = new ArrayList<>();
        for (FavoriteEvent each : favoriteEvents) {
            FavoriteEventDTO favoriteEventDTO = toDTO(each);
            favoriteEventDTOs.add(favoriteEventDTO);
        }
        return favoriteEventDTOs;
    }
}
