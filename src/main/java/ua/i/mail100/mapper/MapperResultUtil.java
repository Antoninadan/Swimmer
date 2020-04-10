package ua.i.mail100.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.i.mail100.dto.ResultDTO;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.model.Result;
import ua.i.mail100.service.DistanceService;
import ua.i.mail100.service.ResultService;
import ua.i.mail100.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperResultUtil {
    @Autowired
    ResultService resultService;

    @Autowired
    DistanceService distanceService;

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public Result toObject(ResultDTO resultDTO) {
        Result result = new Result();
        result.setId(resultDTO.getId());
        result.setDistance(resultDTO.getDistanceId() != null ?
                distanceService.getById(resultDTO.getDistanceId()) : null);
        result.setUser(resultDTO.getUserId() != null ?
                userService.getById(resultDTO.getUserId()) : null);
        result.setTimeInSeconds(resultDTO.getTimeInSeconds());
        result.setComment(resultDTO.getComment());
        return result;
    }

    public ResultDTO toDTO(String request) {
        try {
            return objectMapper.readValue(request, ResultDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultDTO toDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setDistanceId(result.getDistance() != null ?
                result.getDistance().getId() : null);
        resultDTO.setUserId(result.getUser() != null ?
                result.getUser().getId() : null);
        resultDTO.setTimeInSeconds(result.getTimeInSeconds());
        resultDTO.setComment(result.getComment());
        resultDTO.setCreateDate(result.getCreateDate());
        resultDTO.setModifyDate(result.getModifyDate());
        resultDTO.setRecordStatus(result.getRecordStatus().toString());
        return resultDTO;
    }

    public List<ResultDTO> toDTOList(List<Result> results) {
        List<ResultDTO> resultDTOs = new ArrayList<>();
        for (Result each : results) {
            ResultDTO resultDTO = toDTO(each);
            resultDTOs.add(resultDTO);
        }
        return resultDTOs;
    }
}
