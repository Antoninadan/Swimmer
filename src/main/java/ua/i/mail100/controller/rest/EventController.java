package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.service.EventService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("event")
@Slf4j
@Profile("rest")
public class EventController {
    @Autowired
    EventService eventService;

    @Autowired
    MapperEventUtil mapperEventUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String requestBody) {
        EventDTO eventDTO = mapperEventUtil.toDTO(requestBody);
        Event event = mapperEventUtil.toObject(eventDTO);
        Event savedEvent = eventService.save(event);
        if (savedEvent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        EventDTO resultDTO = mapperEventUtil.toDTO(savedEvent);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody String requestBody) {
        EventDTO eventDTO = mapperEventUtil.toDTO(requestBody);
        Event event = mapperEventUtil.toObject(eventDTO);
        Event updatedEvent = eventService.update(event);
        if (updatedEvent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        EventDTO resultDTO = mapperEventUtil.toDTO(updatedEvent);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity getEvent(@PathVariable Integer id) {
        Event event = eventService.getById(id);
        if (event == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        EventDTO resultDTO = mapperEventUtil.toDTO(event);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping({"", "{modifyDate}"})
    public ResponseEntity getAll(@PathVariable(required = false) Long modifyDate) {
        List<Event> events = eventService.getAll(modifyDate);
        List<EventDTO> eventDTOS = mapperEventUtil.toDTOList(events);
        return new ResponseEntity(eventDTOS, HttpStatus.OK);
    }
}
