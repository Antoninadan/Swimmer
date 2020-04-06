package ua.i.mail100.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.i.mail100.dto.ResultDTO;
import ua.i.mail100.mapper.MapperResultUtil;
import ua.i.mail100.model.Result;
import ua.i.mail100.service.ResultService;

import java.util.List;

@RestController
@RequestMapping("result")
@Slf4j
@Profile("rest")
public class ResultController {
    @Autowired
    ResultService resultService;

    @Autowired
    MapperResultUtil mapperResultUtil;

    @PutMapping
    public ResponseEntity save(@RequestBody String requestBody) {
        ResultDTO resultDTO = mapperResultUtil.toDTO(requestBody);
        Result result = mapperResultUtil.toObject(resultDTO);
        Result savedResult = resultService.save(result);
        if (savedResult == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ResultDTO sevedResultDTO = mapperResultUtil.toDTO(savedResult);
        return new ResponseEntity(sevedResultDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody String requestBody) {
        ResultDTO resultDTO = mapperResultUtil.toDTO(requestBody);
        Result result = mapperResultUtil.toObject(resultDTO);
        Result updatedResult = resultService.update(result);
        if (updatedResult == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ResultDTO sevedResultDTO = mapperResultUtil.toDTO(updatedResult);
        return new ResponseEntity(sevedResultDTO, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getResult(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Result result = resultService.getById(id);
            if (result == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            ResultDTO resultDTO = mapperResultUtil.toDTO(result);
            return new ResponseEntity(resultDTO, HttpStatus.OK);
        } else {
            List<Result> results = resultService.getAll();
            List<ResultDTO> resultDTOS = mapperResultUtil.toDTOList(results);
            return new ResponseEntity(resultDTOS, HttpStatus.OK);
        }
    }
}
