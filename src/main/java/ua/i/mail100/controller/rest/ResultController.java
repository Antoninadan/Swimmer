package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.ResultDTO;
import ua.i.mail100.mapper.MapperResultUtil;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.Result;
import ua.i.mail100.model.User;
import ua.i.mail100.service.DistanceService;
import ua.i.mail100.service.ResultService;
import ua.i.mail100.service.UserService;

import java.util.List;

@RestController
@RequestMapping("direct-result")
@Slf4j
public class ResultController {
    @Autowired
    ResultService resultService;

    @Autowired
    UserService userService;

    @Autowired
    DistanceService distanceService;

    @Autowired
    MapperResultUtil mapperResultUtil;

    @PutMapping("save")
    public ResponseEntity save(@RequestBody String requestBody) {
        ResultDTO resultDTO = mapperResultUtil.toDTO(requestBody);
        Result result = mapperResultUtil.toObject(resultDTO);
        if (!resultService.isResultComplete(result))
            return new ResponseEntity("not complete result data", HttpStatus.BAD_REQUEST);
        Distance distance = result.getDistance();
        if (!distanceService.isDistanceAvailable(distance))
            return new ResponseEntity("event or distance is deleted", HttpStatus.BAD_REQUEST);
        if (!distanceService.isDistanceDateHasCome(distance))
            return new ResponseEntity("distance has not come", HttpStatus.BAD_REQUEST);

        User user = userService.getById(result.getUser().getId());
        if (user == null)
            return new ResponseEntity("user not found", HttpStatus.BAD_REQUEST);
        if (!userService.isUserAvailability(user))
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);


        Result savedResult = resultService.save(result);
        if (savedResult == null)
            return new ResponseEntity("result not saved", HttpStatus.BAD_REQUEST);
        ResultDTO savedResultDTO = mapperResultUtil.toDTO(savedResult);
        return new ResponseEntity(savedResultDTO, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody String requestBody) {
        ResultDTO resultDTO = mapperResultUtil.toDTO(requestBody);
        Result result = mapperResultUtil.toObjectForUpdate(resultDTO);
        if (!resultService.isResultAvailable(result))
            return new ResponseEntity("result not available", HttpStatus.BAD_REQUEST);
        if (!resultService.isResultComplete(result))
            return new ResponseEntity("not complete result data", HttpStatus.BAD_REQUEST);
        Distance distance = result.getDistance();
        if (!distanceService.isDistanceAvailable(distance))
            return new ResponseEntity("event or distance is deleted", HttpStatus.BAD_REQUEST);
        if (!distanceService.isDistanceDateHasCome(distance))
            return new ResponseEntity("distance has not come", HttpStatus.BAD_REQUEST);

        User user = userService.getById(result.getUser().getId());
        if (user == null)
            return new ResponseEntity("user not found", HttpStatus.BAD_REQUEST);
        if (!userService.isUserAvailability(user))
            return new ResponseEntity("user not available", HttpStatus.FORBIDDEN);

        Result updatedResult = resultService.update(result);
        if (updatedResult == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ResultDTO savedResultDTO = mapperResultUtil.toDTO(updatedResult);
        return new ResponseEntity(savedResultDTO, HttpStatus.OK);
    }


    @GetMapping("by-id/{id}")
    public ResponseEntity getResult(@PathVariable Integer id) {
        Result result = resultService.getById(id);
        if (result == null) {
            return new ResponseEntity("record not found", HttpStatus.NOT_FOUND);
        }
        ResultDTO resultDTO = mapperResultUtil.toDTO(result);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping("all/{userId}/{distanceId}")
    public ResponseEntity getAllByDistance(@PathVariable Integer userId,
                                           @PathVariable Integer distanceId) {
        List<Result> results = resultService.getAllByUserAndDistanceAndModifiedAfter(userId, distanceId, null);
        List<ResultDTO> resultDTOS = mapperResultUtil.toDTOList(results);
        return new ResponseEntity(resultDTOS, HttpStatus.OK);
    }

    @GetMapping({"all/{userId}", "all/{userId}/{distanceId}/{modifyDate}"})
    public ResponseEntity getAllByDistanceAndModifiedAfter(@PathVariable Integer userId,
                                                           @PathVariable(required = false) Integer distanceId,
                                                           @PathVariable(required = false) Long modifyDate) {
        List<Result> results = resultService.getAllByUserAndDistanceAndModifiedAfter(userId, distanceId,
                modifyDate);
        List<ResultDTO> resultDTOS = mapperResultUtil.toDTOList(results);
        return new ResponseEntity(resultDTOS, HttpStatus.OK);
    }


}
