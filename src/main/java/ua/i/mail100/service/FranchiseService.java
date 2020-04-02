package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.FranchiseDAO;
import ua.i.mail100.model.Franchise;

import java.util.Optional;

@Service
public class FranchiseService {
    @Autowired
    FranchiseDAO franchiseDAO;

    public Franchise getById(Integer id) {
        Optional<Franchise> franchise = franchiseDAO.findById(id);

        if (franchise.isEmpty()) {
            return null;
        }
        return franchise.get();
    }

    public Franchise save(Franchise franchise) {
        if (franchise.getId() == null && franchiseDAO.getFirstByName(franchise.getName()) == null) {
            return franchiseDAO.save(franchise);
        }
        return null;
    }

    public Franchise update(Franchise franchise) {
        if (franchise.getId() != null && franchiseDAO.getOne(franchise.getId()) != null) {
            return franchiseDAO.save(franchise);
        }
        return null;
    }

    public void delete(Franchise franchise) {
        franchiseDAO.delete(franchise);
    }

}
