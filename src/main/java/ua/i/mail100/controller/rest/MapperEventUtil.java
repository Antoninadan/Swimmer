package ua.i.mail100.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.service.CountryService;
import ua.i.mail100.service.FranchiseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperEventUtil {
    @Autowired
    FranchiseService franchiseService;

    @Autowired
    CountryService countryService;

    @Autowired
    ObjectMapper objectMapper;

    public Event toObject(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setFranchise(eventDTO.getFranchiseId() != null ?
                franchiseService.getById(eventDTO.getFranchiseId()) : null);
        event.setOrganizer(eventDTO.getOrganizer());
        event.setName(eventDTO.getName());
        event.setDateFrom(eventDTO.getDateFrom());
        event.setDateTo(eventDTO.getDateTo());
        event.setCountry(eventDTO.getCountryId() != null ?
                countryService.getById(eventDTO.getCountryId()) : null);
        event.setVenue(eventDTO.getVenue());
        event.setUrl(eventDTO.getUrl());
        event.setComment(eventDTO.getComment());
        event.setCreateDate(eventDTO.getCreateDate());
        event.setModifyDate(eventDTO.getModifyDate());
        event.setRecordStatus(RecordStatus.valueOf(eventDTO.getRecordStatus()));
        return event;
    }

    public EventDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, EventDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EventDTO toDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setFranchiseId(event.getFranchise() != null ? event.getFranchise().getId() : null);
        eventDTO.setOrganizer(event.getOrganizer());
        eventDTO.setName(event.getName());
        eventDTO.setDateFrom(event.getDateFrom());
        eventDTO.setDateTo(event.getDateTo());
        eventDTO.setCountryId(event.getCountry() != null ? event.getCountry().getId() : null);
        eventDTO.setVenue(event.getVenue());
        eventDTO.setUrl(event.getUrl());
        eventDTO.setComment(event.getComment());
        eventDTO.setCreateDate(event.getCreateDate());
        eventDTO.setModifyDate(event.getModifyDate());
        eventDTO.setRecordStatus(event.getRecordStatus().toString());
        return eventDTO;
    }

    public List<EventDTO> toDTOList(List<Event> events) {
        List<EventDTO> orderDTOs = new ArrayList<>();
        for (Event each : events) {
            EventDTO eventDTO = toDTO(each);
            orderDTOs.add(eventDTO);
        }
        return orderDTOs;
    }
}
