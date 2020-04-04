package ua.i.mail100.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.model.AgeDistanceType;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.DistanceType;
import ua.i.mail100.service.CountryService;
import ua.i.mail100.service.EventService;
import ua.i.mail100.service.FranchiseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperDistanceUtil {
    @Autowired
    EventService eventService;

    @Autowired
    ObjectMapper objectMapper;

    public Distance toObject(DistanceDTO distanceDTO) {
        Distance distance = new Distance();
        distance.setId(distanceDTO.getId());
        distance.setEvent(distanceDTO.getEventId() != null ?
                eventService.getById(distanceDTO.getEventId()) : null);
        distance.setDistanceType(distanceDTO.getDistanceType() != null ?
                DistanceType.valueOf(distanceDTO.getDistanceType()) : null);
        distance.setAgeDistanceType(distanceDTO.getAgeDistanceType() != null ?
                AgeDistanceType.valueOf(distanceDTO.getAgeDistanceType()) : null);
        distance.setLengthInMeters(distanceDTO.getLengthInMeters());
        distance.setDate(distanceDTO.getDate());
        distance.setComment(distanceDTO.getComment());
        return distance;
    }

    public DistanceDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, DistanceDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DistanceDTO toDTO(Distance distance) {
        DistanceDTO distanceDTO = new DistanceDTO();
        distanceDTO.setId(distance.getId());
        distanceDTO.setEventId(distance.getEvent() != null ?
                distance.getEvent().getId() : null);
        distanceDTO.setDistanceType(distance.getDistanceType() != null ?
                distance.getDistanceType().toString() : null);
        distanceDTO.setAgeDistanceType(distance.getAgeDistanceType() != null ?
                distance.getAgeDistanceType().toString() : null);
        distanceDTO.setLengthInMeters(distance.getLengthInMeters());
        distanceDTO.setDate(distance.getDate());
        distanceDTO.setComment(distance.getComment());
        return distanceDTO;
    }

    public List<DistanceDTO> toDTOList(List<Distance> distances) {
        List<DistanceDTO> distanceDTOs = new ArrayList<>();
        for (Distance each : distances) {
            DistanceDTO distanceDTO = toDTO(each);
            distanceDTOs.add(distanceDTO);
        }
        return distanceDTOs;
    }
}
