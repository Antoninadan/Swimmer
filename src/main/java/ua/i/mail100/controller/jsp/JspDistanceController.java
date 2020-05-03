package ua.i.mail100.controller.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.i.mail100.dto.DistanceDTO;
import ua.i.mail100.mapper.*;
import ua.i.mail100.model.*;
import ua.i.mail100.service.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("distance")
public class JspDistanceController {
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

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
    public String openDistances(Model model,
                                @RequestParam(value = "userId") String userId,
                                @RequestParam(value = "eventId") String eventId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";

        distancesMake(model, Integer.valueOf(eventId));
        return "distances";
    }

    @GetMapping("open-for-edit")
    public String openForEdit(Model model,
                              @RequestParam(value = "userId") String userId,
                              @RequestParam(value = "eventId") String eventId,
                              @RequestParam(value = "distanceId") String distanceId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!distanceCheckAndMake(model, distanceId, eventId)) return "events";

        List distanceTypeList = Arrays.asList(DistanceType.values());
        model.addAttribute("distanceTypeList", distanceTypeList);

        List ageDistanceTypeList = Arrays.asList(AgeDistanceType.values());
        model.addAttribute("ageDistanceTypeList", ageDistanceTypeList);

        model.addAttribute("eventId", getEventId(distanceId));
        return "distance-edit";
    }

    @GetMapping("open-for-save")
    public String openForSave(Model model,
                              @RequestParam(value = "userId") String userId,
                              @RequestParam(value = "eventId") String eventId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";

        List distanceTypeList = Arrays.asList(DistanceType.values());
        model.addAttribute("distanceTypeList", distanceTypeList);

        List ageDistanceTypeList = Arrays.asList(AgeDistanceType.values());
        model.addAttribute("ageDistanceTypeList", ageDistanceTypeList);

        model.addAttribute("eventId", Integer.valueOf(eventId));

        return "distance-save";
    }

    @PostMapping("save")
    public String save(Model model,
                       @RequestParam(value = "userId") String userId,
                       @RequestParam(value = "eventId") String eventId,
                       @RequestParam(value = "distanceType") String distanceType,
                       @RequestParam(value = "ageDistanceType") String ageDistanceType,
                       @RequestParam(value = "lengthInMeters") String lengthInMeters,
                       @RequestParam(value = "date") String date,
                       @RequestParam(value = "comment") String comment) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!eventCheckAndMake(model, eventId)) return "distances";

        Integer eventIdSaved = Integer.valueOf(eventId);
        Event event = eventService.getById(eventIdSaved);
        DistanceType distanceTypeInputed = DistanceType.valueOf(distanceType);
        AgeDistanceType ageDistanceTypeInputed = AgeDistanceType.valueOf(ageDistanceType);
        Integer lengthInMetersInputed = Integer.valueOf(lengthInMeters);
        Date dateInputed = dateService.parse(date);

        Distance distance = new Distance(null, event, distanceTypeInputed, ageDistanceTypeInputed,
                lengthInMetersInputed, dateInputed, comment,
                null, null, null);

        Distance savedDistance = distanceService.save(distance);
        if (!distanceCheckAndMake(model, savedDistance, savedDistance.getEvent().getId())) return "distance-save";
        distancesMake(model, eventIdSaved);
        return "distances";
    }

    public boolean eventCheckAndMake(Model model, String eventId) {
        Integer eventIdSelected = Integer.valueOf(eventId);
        Event event = eventService.getById(eventIdSelected);
        if (event != null) {
            model.addAttribute("eventId", event.getId());
            return true;
        } else {
            model.addAttribute("message", "Record are wrong!");
            return false;
        }
    }

    @PostMapping("update")
    public String update(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "eventId") String eventId,
                         @RequestParam(value = "distanceId") String distanceId,
                         @RequestParam(value = "distanceType") String distanceType,
                         @RequestParam(value = "ageDistanceType") String ageDistanceType,
                         @RequestParam(value = "lengthInMeters") String lengthInMeters,
                         @RequestParam(value = "date") String date,
                         @RequestParam(value = "comment") String comment) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!distanceCheckAndMake(model, distanceId, eventId)) return "distances";

        Distance distance = distanceService.getById(Integer.valueOf(distanceId));
        DistanceType distanceTypeInputed = DistanceType.valueOf(distanceType);
        AgeDistanceType ageDistanceTypeInputed = AgeDistanceType.valueOf(ageDistanceType);
        Integer lengthInMetersInputed = Integer.valueOf(lengthInMeters);
        Date dateInputed = dateService.parse(date);

        distance.setDistanceType(distanceTypeInputed);
        distance.setAgeDistanceType(ageDistanceTypeInputed);
        distance.setLengthInMeters(lengthInMetersInputed);
        distance.setDate(dateInputed);
        distance.setComment(comment);

        Distance updatedDistance = distanceService.update(distance);
        if (!distanceCheckAndMake(model, updatedDistance, updatedDistance.getEvent().getId()))
            return "distance-edit";
        distancesMake(model, updatedDistance.getEvent().getId());
        return "distances";
    }

    @PostMapping("delete")
    public String delete(Model model,
                         @RequestParam(value = "userId") String userId,
                         @RequestParam(value = "eventId") String eventId,
                         @RequestParam(value = "distanceId") String distanceId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!distanceCheckAndMake(model, distanceId, eventId)) return "distances";

        try {
            distanceService.delete(Integer.valueOf(distanceId));
        } catch (Exception e) {
            model.addAttribute("message", "Record cannot be deleted!");
            e.printStackTrace();
            return "event-edit";
        }
        distancesMake(model, Integer.valueOf(eventId));
        return "distances";
    }

    @PostMapping("soft-delete")
    public String softDelete(Model model,
                             @RequestParam(value = "userId") String userId,
                             @RequestParam(value = "eventId") String eventId,
                             @RequestParam(value = "distanceId") String distanceId) {
        if (!jspService.userCheckAndMake(model, userId)) return "authorization";
        if (!distanceCheckAndMake(model, distanceId, eventId)) return "distances";

        Distance softDeletedDistance = distanceService.softDelete(Integer.valueOf(distanceId));
        if (!distanceCheckAndMake(model, softDeletedDistance, softDeletedDistance.getEvent().getId()))
            return "distances";
        distancesMake(model, getEventId(distanceId));
        return "distances";
    }


    public boolean distanceCheckAndMake(Model model, Distance distance, Integer eventId) {
        if (distance != null) {
            DistanceDTO distanceDTO = mapperDistanceUtil.toDTO(distance);
            model.addAttribute("distance", distanceDTO);
            return true;
        } else {
            List<Distance> distances = distanceService.getAllByEvent(eventId);
            List<DistanceDTO> distanceDTOS = mapperDistanceUtil.toDTOList(distances);
            model.addAttribute("distances", distanceDTOS);

            model.addAttribute("message", "Record are wrong!");
            return false;
        }
    }

    public boolean distanceCheckAndMake(Model model, String distanceId, String eventId) {
        Integer distanceIdSelected = Integer.valueOf(distanceId);
        Distance distance = distanceService.getById(distanceIdSelected);
        return distanceCheckAndMake(model, distance, Integer.valueOf(eventId));
    }


    public void distancesMake(Model model, Integer eventId) {
        List<Distance> distances = distanceService.getAllByEvent(eventId);
        List<DistanceDTO> distanceDTOS = mapperDistanceUtil.toDTOList(distances);
        model.addAttribute("distances", distanceDTOS);

        model.addAttribute("eventId", eventId);
    }

    public Integer getEventId(String distanceId) {
        Distance distance = distanceService.getById(Integer.valueOf(distanceId));
        return distance.getEvent().getId();
    }
}