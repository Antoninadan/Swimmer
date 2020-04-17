package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.mapper.MapperEventUtil;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.service.EventService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("event")
@Slf4j
@Profile("rest")
public class EventController {
    @Autowired
    EventService eventService;

    @Autowired
    MapperEventUtil mapperEventUtil;

    @PutMapping("save")
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

    @PostMapping("update")
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

    @PostMapping("soft-delete")
    public ResponseEntity softDelete(@RequestBody String requestBody) {
        Map<String, Object> map = new JacksonJsonParser().parseMap(requestBody);
        Integer id = (Integer) (map.get("id"));
        Event event = eventService.getById(id);
        if (event == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Event softDeletedEvent = eventService.softDelete(event);
        if (softDeletedEvent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        EventDTO resultDTO = mapperEventUtil.toDTO(softDeletedEvent);
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

    @GetMapping({"all/", "all/{modifyDate}"})
    public ResponseEntity getAllModifiedAfter(@PathVariable(required = false) Long modifyDate) {
        List<Event> events = eventService.getAllModifiedAfter(modifyDate);
        List<EventDTO> eventDTOS = mapperEventUtil.toDTOList(events);
        return new ResponseEntity(eventDTOS, HttpStatus.OK);
    }
}
