package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.dto.FranchiseDTO;
import ua.i.mail100.mapper.MapperEventUtil;
import ua.i.mail100.mapper.MapperFranchiseUtil;
import ua.i.mail100.mapper.MapperUserUtil;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.presenter.EventPresenter;
import ua.i.mail100.service.DateService;
import ua.i.mail100.service.EventService;
import ua.i.mail100.service.FranchiseService;
import ua.i.mail100.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("event")
public class JspEventController {
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    FranchiseService franchiseService;

    @Autowired
    JspService jspService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @Autowired
    MapperEventUtil mapperEventUtil;

    @Autowired
    MapperFranchiseUtil mapperFranchiseUtil;

    @Autowired
    DateService dateService;

    @GetMapping("open-all")
    public String openEvents(Model model,
                                @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        eventsMake(model);
        return "events";
    }

    @GetMapping("open-for-edit")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId,
                              @RequestParam(value = "eventId") String eventId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!eventCheckAndMake(model, eventId)) return "events";
        return "event-edit";
    }

    @GetMapping("open-for-save")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        List<Franchise> franchises = franchiseService.getAll(0L);
        List<FranchiseDTO> franchiseDTOS = mapperFranchiseUtil.toDTOList(franchises);
        model.addAttribute("franchiseList", franchiseDTOS);
        return "event-save";
    }

//    @PostMapping("update")
//    public String update(Model model,
//                         @RequestParam(value = "userId") String userId,
//                         @RequestParam(value = "eventId") String eventId,
//                         @RequestParam(value = "name") String name) {
//        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
//        if (!eventCheckAndMake(model, eventId)) return "events";
//
//        Event event = eventService.getById(Integer.valueOf(eventId));
//        event.setName(name);
//        if (!eventService.noEventWithSameName(event)) {
//            model.addAttribute("event", event);
//            model.addAttribute("message", "Name not unique!");
//            return "event-edit";
//        }
//
//        Event updatedEvent = eventService.update(event);
//        if (!eventCheckAndMake(model, updatedEvent)) return "event-edit";
//        eventsMake(model);
//        return "events";
//    }
//
//    @PostMapping("save")
//    public String save(Model model,
//                       @RequestParam(value = "userId") String userId,
//                       @RequestParam(value = "name") String name) {
//        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
//
//        Event event = new Event(null, name,
//                null, null, null);
//        if (!eventService.noEventWithSameName(event)) {
//            model.addAttribute("message", "Name not unique!");
//            return "event-save";
//        }
//
//        Event savedEvent = eventService.save(event);
//        if (!eventCheckAndMake(model, savedEvent)) return "event-edit";
//        eventsMake(model);
//        return "events";
//    }

//    @PostMapping("delete")
//    public String delete(Model model,
//                         @RequestParam(value = "userId") String userId,
//                         @RequestParam(value = "eventId") String eventId) {
//        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
//        if (!eventCheckAndMake(model, eventId)) return "events";
//
//        try {
//            eventService.deleteById(Integer.valueOf(eventId));
//        } catch (Exception e) {
//            model.addAttribute("message", "Record cannot be deleted!");
//            e.printStackTrace();
//            return "event-edit";
//        }
//        eventsMake(model);
//        return "events";
//    }

    public boolean eventCheckAndMake(Model model, Event event) {
        if (event != null) {
            EventDTO eventDTO = mapperEventUtil.toDTO(event);
            model.addAttribute("event", eventDTO);
            return true;
        } else {
            model.addAttribute("message", "Record are wrong!");
            return false;
        }
    }

    public boolean eventCheckAndMake(Model model, String eventId) {
        Integer eventIdSelected = Integer.valueOf(eventId);
        Event event = eventService.getById(eventIdSelected);
        return eventCheckAndMake(model, event);
    }

    public void eventsMake(Model model) {
        List<Event> events = eventService.getAllModifiedAfter(0L);
        List<EventPresenter> eventPresenters = mapperEventUtil.toPresenterList(events);
        model.addAttribute("events", eventPresenters);
    }
}

// TODO normal date control
// TODO check login unique