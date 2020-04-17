package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.i.mail100.dto.CountryDTO;
import ua.i.mail100.dto.DistanceDTO;
import ua.i.mail100.dto.EventDTO;
import ua.i.mail100.dto.FranchiseDTO;
import ua.i.mail100.mapper.*;
import ua.i.mail100.model.Country;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Event;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.presenter.EventPresenter;
import ua.i.mail100.service.*;

import java.util.ArrayList;
import java.util.Date;
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
    CountryService countryService;

    @Autowired
    DistanceService distanceService;

    @Autowired
    JspService jspService;

    @Autowired
    MapperUserUtil mapperUserUtil;

    @Autowired
    MapperEventUtil mapperEventUtil;

    @Autowired
    MapperFranchiseUtil mapperFranchiseUtil;

    @Autowired
    MapperCountryUtil mapperCountryUtil;

    @Autowired
    MapperDistanceUtil mapperDistanceUtil;

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

        List<Franchise> franchises = franchiseService.getAll(0L);
        List<FranchiseDTO> franchiseDTOS = mapperFranchiseUtil.toDTOList(franchises);
        model.addAttribute("franchiseList", franchiseDTOS);

        List<Country> countries = countryService.getAll(0L);
        List<CountryDTO> countryDTOS = mapperCountryUtil.toDTOList(countries);
        model.addAttribute("countryList", countryDTOS);

//        distancesMake(model, Integer.valueOf(eventId));
        return "event-edit";
    }

    @GetMapping("open-for-save")
    public String openForSave(Model model,
                              @RequestParam(value = "userId") String userId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";

        List<Franchise> franchises = franchiseService.getAll(0L);
        List<FranchiseDTO> franchiseDTOS = mapperFranchiseUtil.toDTOList(franchises);
        model.addAttribute("franchiseList", franchiseDTOS);

        List<Country> countries = countryService.getAll(0L);
        List<CountryDTO> countryDTOS = mapperCountryUtil.toDTOList(countries);
        model.addAttribute("countryList", countryDTOS);
        return "event-save";
    }

    @PostMapping("save")
    public String save(Model model,
                       @RequestParam(value = "userId") String userId,
                       @RequestParam(value = "franchise") String franchiseIdStr,
                       @RequestParam(value = "organizer") String organizer,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "date_from") String dateFromStr,
                       @RequestParam(value = "date_to") String dateToStr,
                       @RequestParam(value = "country") String countryIdStr,
                       @RequestParam(value = "venue") String venue,
                       @RequestParam(value = "url") String url,
                       @RequestParam(value = "comment") String comment) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        Franchise franchise = franchiseService.getById(Integer.valueOf(franchiseIdStr));
        Country country = countryService.getById(Integer.valueOf(countryIdStr));
        Date dateFrom = dateService.parse(dateFromStr);
        Date dateTo = dateService.parse(dateToStr);
        Event event = new Event(null, franchise, organizer, name, dateFrom, dateTo,
                country, venue, url, comment,
                null, null, null);

        Event savedEvent = eventService.save(event);
        if (!eventCheckAndMake(model, savedEvent)) return "event-edit";
        eventsMake(model);
        return "events";
    }

    @PostMapping("update")
    public String update(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "eventId") String eventId,
                         @RequestParam(value = "franchise") String franchiseIdStr,
                         @RequestParam(value = "organizer") String organizer,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "date_from") String dateFromStr,
                         @RequestParam(value = "date_to") String dateToStr,
                         @RequestParam(value = "country") String countryIdStr,
                         @RequestParam(value = "venue") String venue,
                         @RequestParam(value = "url") String url,
                         @RequestParam(value = "comment") String comment) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!eventCheckAndMake(model, eventId)) return "events";

        Event event = eventService.getById(Integer.valueOf(eventId));
        Franchise franchise = franchiseService.getById(Integer.valueOf(franchiseIdStr));
        Country country = countryService.getById(Integer.valueOf(countryIdStr));
        Date dateFrom = dateService.parse(dateFromStr);
        Date dateTo = dateService.parse(dateToStr);
        event.setFranchise(franchise);
        event.setOrganizer(organizer);
        event.setName(name);
        event.setDateFrom(dateFrom);
        event.setDateTo(dateTo);
        event.setCountry(country);
        event.setVenue(venue);
        event.setUrl(url);
        event.setComment(comment);

        Event updatedEvent = eventService.update(event);
        if (!eventCheckAndMake(model, updatedEvent)) return "event-edit";
        eventsMake(model);
        return "events";
    }

    @PostMapping("delete")
    public String delete(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "eventId") String eventId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!eventCheckAndMake(model, eventId)) return "events";

        try {
            eventService.delete(Integer.valueOf(eventId));
        } catch (Exception e) {
            model.addAttribute("message", "Record cannot be deleted!");
            e.printStackTrace();
            return "event-edit";
        }
        eventsMake(model);
        return "events";
    }

    @PostMapping("soft-delete")
    public String softDelete(Model model,
                             @RequestParam(value = "userId") String userId,
                             @RequestParam(value = "eventId") String eventId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!eventCheckAndMake(model, eventId)) return "events";

        Event softDeletedEvent = eventService.softDelete(Integer.valueOf(eventId));
        if (!eventCheckAndMake(model, softDeletedEvent)) return "events";
        eventsMake(model);
        return "events";
    }

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

    public void distancesMake(Model model, Integer eventId) {
        List<Distance> distances = distanceService.getAllByEvent(eventId);
        List<DistanceDTO> distanceDTOS = mapperDistanceUtil.toDTOList(distances);
        model.addAttribute("distances", distanceDTOS);
    }

}