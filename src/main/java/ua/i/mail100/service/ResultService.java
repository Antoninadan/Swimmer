package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.ResultDAO;
import ua.i.mail100.model.Distance;
import ua.i.mail100.model.RecordStatus;
import ua.i.mail100.model.Result;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    ResultDAO resultDAO;

    public Result getById(Integer id) {
        Optional<Result> result = resultDAO.findById(id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get();
    }

    public Result save(Result result) {
        if (result.getId() == null) {
            Long now = new Date().getTime();
            result.setCreateDate(now);
            result.setModifyDate(now);
            result.setRecordStatus(RecordStatus.ACTIVE);
            return resultDAO.save(result);
        }
        return null;
    }

    public Result update(Result result) {
        Integer resultId = result.getId();
        if (resultId != null) {
            Result savedEarlierResult = resultDAO.getOne(resultId);
            if (savedEarlierResult != null) {
                Long now = new Date().getTime();
                result.setRecordStatus(savedEarlierResult.getRecordStatus());
                result.setCreateDate(savedEarlierResult.getCreateDate());
                result.setModifyDate(now);
                return resultDAO.save(result);
            }
            return null;
        }
        return null;
    }

    public void delete(Result result) {
        resultDAO.delete(result);
    }

    public List<Result> getAll() {
        return resultDAO.findAll();
    }


    public boolean isResultComplete(Result result) {
        if (result.getUser() == null) return false;
        if (result.getDistance() == null) return false;
        if (result.getTimeInSeconds() == null) return false;
        return true;
    }
}
