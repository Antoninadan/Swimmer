package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.presenter.EventPresenter;
import ua.i.mail100.service.CountryService;
import ua.i.mail100.service.DateService;
import ua.i.mail100.service.DistanceService;
import ua.i.mail100.service.FranchiseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MapperEventUtil {
    @Autowired
    FranchiseService franchiseService;

    @Autowired
    CountryService countryService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MapperDistanceUtil mapperDistanceUtil;

    @Autowired
    DistanceService distanceService;

    @Autowired
    DateService dateService;

    public Event toObject(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setFranchise(eventDTO.getFranchiseId() != null ?
                franchiseService.getById(eventDTO.getFranchiseId()) : null);
        event.setOrganizer(eventDTO.getOrganizer());
        event.setName(eventDTO.getName());
        event.setDateFrom(dateService.parse(eventDTO.getDateFrom()));
        event.setDateTo(dateService.parse(eventDTO.getDateTo()));
        event.setCountry(eventDTO.getCountryId() != null ?
                countryService.getById(eventDTO.getCountryId()) : null);
        event.setVenue(eventDTO.getVenue());
        event.setUrl(eventDTO.getUrl());
        event.setComment(eventDTO.getComment());
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
        eventDTO.setDateFrom(dateService.toString(event.getDateFrom()));
        eventDTO.setDateTo(dateService.toString(event.getDateTo()));
        eventDTO.setCountryId(event.getCountry() != null ? event.getCountry().getId() : null);
        eventDTO.setVenue(event.getVenue());
        eventDTO.setUrl(event.getUrl());
        eventDTO.setComment(event.getComment());
        eventDTO.setCreateDate(event.getCreateDate());
        eventDTO.setModifyDate(event.getModifyDate());
        eventDTO.setRecordStatus(event.getRecordStatus().toString());
        eventDTO.setDistances(event.getId() != null ?
                mapperDistanceUtil.toDTOList(distanceService.getAllByEvent(event.getId())) : null);
        return eventDTO;
    }

    public List<EventDTO> toDTOList(List<Event> events) {
        Collections.sort(events);
        List<EventDTO> orderDTOs = new ArrayList<>();
        for (Event each : events) {
            EventDTO eventDTO = toDTO(each);
            orderDTOs.add(eventDTO);
        }
        return orderDTOs;
    }

    public EventPresenter toPresenter(Event event) {
        EventPresenter eventPresenter = new EventPresenter();
        eventPresenter.setId(event.getId());
        eventPresenter.setFranchise(event.getFranchise() != null ? event.getFranchise().getName() : null);
        eventPresenter.setOrganizer(event.getOrganizer());
        eventPresenter.setName(event.getName());
        eventPresenter.setDateFrom(dateService.toString(event.getDateFrom()));
        eventPresenter.setDateTo(dateService.toString(event.getDateTo()));
        eventPresenter.setCountry(event.getCountry() != null ? event.getCountry().getName() : null);
        eventPresenter.setVenue(event.getVenue());
        eventPresenter.setUrl(event.getUrl());
        eventPresenter.setComment(event.getComment());
        eventPresenter.setCreateDate(event.getCreateDate());
        eventPresenter.setModifyDate(event.getModifyDate());
        eventPresenter.setRecordStatus(event.getRecordStatus().toString());
//        eventPresenter.setDistances(event.getId() != null ?
//                mapperDistanceUtil.toDTOList(distanceService.getAllByEvent(event.getId())) : null);
        return eventPresenter;
    }

    public List<EventPresenter> toPresenterList(List<Event> events) {
        Collections.sort(events);
        List<EventPresenter> eventPresenters = new ArrayList<>();
        for (Event each : events) {
            EventPresenter eventPresenter = toPresenter(each);
            eventPresenters.add(eventPresenter);
        }
        return eventPresenters;
    }
}
