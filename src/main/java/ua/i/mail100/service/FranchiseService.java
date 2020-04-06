package ua.i.mail100.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.FranchiseDAO;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.RecordStatus;

import java.util.Date;
import java.util.List;
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
        if (franchise.getId() == null && noFranchiseWithSameName(franchise)) {
            Long now = new Date().getTime();
            franchise.setCreateDate(now);
            franchise.setModifyDate(now);
            franchise.setRecordStatus(RecordStatus.ACTIVE);
            return franchiseDAO.save(franchise);
        }
        return null;
    }

    public Franchise update(Franchise franchise) {
        Integer franchiseId = franchise.getId();
        if (franchiseId != null) {
            Franchise savedEarlierFranchise = franchiseDAO.getOne(franchiseId);
            if (savedEarlierFranchise != null & noFranchiseWithSameName(franchise)) {
                Long now = new Date().getTime();
                franchise.setRecordStatus(savedEarlierFranchise.getRecordStatus());
                franchise.setCreateDate(savedEarlierFranchise.getCreateDate());
                franchise.setModifyDate(now);
                return franchiseDAO.save(franchise);
            }
            return null;
        }
        return null;
    }

    public void delete(Franchise franchise) {
        franchiseDAO.delete(franchise);
    }

    private boolean noFranchiseWithSameName(Franchise franchise) {
        if (franchiseDAO.getFirstByName(franchise.getName()) == null)
            return true;
        else return false;
    }

    public List<Franchise> getAll(Long modifyDate) {
        if (modifyDate == null) modifyDate = 0L;
        return franchiseDAO.getAll(modifyDate);
    }

    public void deleteById(Integer id) throws RuntimeException {
        franchiseDAO.deleteById(id);
    }
}
