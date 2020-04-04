package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.ResultDAO;
import ua.i.mail100.model.Result;

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
            return resultDAO.save(result);
        }
        return null;
    }

    public Result update(Result result) {
        if (result.getId() != null && resultDAO.getOne(result.getId()) != null) {
            return resultDAO.save(result);
        }
        return null;
    }

    public void delete(Result result) {
        resultDAO.delete(result);
    }

    public List<Result> getAll() {
        return resultDAO.findAll();
    }
}
