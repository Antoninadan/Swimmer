package ua.i.mail100.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.model.Distance;
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

    private Integer id;
    private Integer eventId;
    private String distanceType;
    private String ageDistanceType;
    private Long date;
    private String comment;


    public Distance toObject(DistanceDTO distanceDTO) {
        Distance distance = new Distance();
        distance.setId(distanceDTO.getId());
        distance.setEvent(eventService.getById(eventDTO.getEventId()));



        distance.setOrganizer(distanceDTO.getOrganizer());
        distance.setName(distanceDTO.getName());
        distance.setDateFrom(distanceDTO.getDateFrom());
        distance.setDateTo(distanceDTO.getDateTo());
        distance.setCountry(countryService.getById(distanceDTO.getCountryId()));
        distance.setVenue(distanceDTO.getVenue());
        distance.setUrl(distanceDTO.getUrl());
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
        distanceDTO.setFranchiseId(distance.getFranchise().getId());
        distanceDTO.setOrganizer(distance.getOrganizer());
        distanceDTO.setName(distance.getName());
        distanceDTO.setDateFrom(distance.getDateFrom());
        distanceDTO.setDateTo(distance.getDateTo());
        distanceDTO.setCountryId(distance.getCountry().getId());
        distanceDTO.setVenue(distance.getVenue());
        distanceDTO.setUrl(distance.getUrl());
        distanceDTO.setComment(distance.getComment());
        return distanceDTO;
    }

    public List<DistanceDTO> toDTOList(List<Distance> events) {
        List<DistanceDTO> orderDTOs = new ArrayList<>();
        for (Distance each : events) {
            DistanceDTO distanceDTO = toDTO(each);
            orderDTOs.add(distanceDTO);
        }
        return orderDTOs;
    }


}
