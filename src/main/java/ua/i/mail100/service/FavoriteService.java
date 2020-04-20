package ua.i.mail100.service;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.i.mail100.dao.FavoriteDAO;
import ua.i.mail100.model.Favorite;
import ua.i.mail100.model.RecordStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    FavoriteDAO favoriteDAO;

    public Favorite getById(Integer id) {
        Optional<Favorite> favoriteEvent = favoriteDAO.findById(id);
        if (favoriteEvent.isEmpty()) {
            return null;
        }
        return favoriteEvent.get();
    }

    public Favorite save(Favorite favorite) {
        if (favorite.getId() == null) {
            Long now = new Date().getTime();
            favorite.setCreateDate(now);
            favorite.setModifyDate(now);
            favorite.setRecordStatus(RecordStatus.ACTIVE);
            return favoriteDAO.save(favorite);
        }
        return null;
    }

    public Favorite update(Favorite favorite) {
        Integer favoriteId = favorite.getId();
        if (favoriteId != null) {
            Favorite savedEarlierFavorite = favoriteDAO.getOne(favoriteId);
            if (savedEarlierFavorite != null) {
                Long now = new Date().getTime();
                favorite.setRecordStatus(savedEarlierFavorite.getRecordStatus());
                favorite.setCreateDate(savedEarlierFavorite.getCreateDate());
                favorite.setModifyDate(now);
                return favoriteDAO.save(favorite);
            }
            return null;
        }
        return null;
    }

    public void delete(Favorite favorite) {
        favoriteDAO.delete(favorite);
    }

    public void deleteById(Integer favoriteId) {
        favoriteDAO.deleteById(favoriteId);
    }

    public Favorite softDelete(Favorite favorite) {
        favorite.setRecordStatus(RecordStatus.DELETED);
        return update(favorite);
    }

    public Favorite softDelete(Integer favoriteId) {
        if (favoriteId != null) {
            Favorite favorite = favoriteDAO.getOne(favoriteId);
            return softDelete(favorite);
        }
        return null;
    }

    public List<Favorite> getAllByUserAndModifiedAfter(Integer userId, Long modifyDate) {
        if (modifyDate == null) modifyDate = 0L;
        return favoriteDAO.getAllByUserAndModifiedAfter(userId, modifyDate);
    }

    public boolean isFavoriteComplete(Favorite favorite) {
        if (favorite.getUser() == null) return false;
        if (favorite.getEvent() == null) return false;
        return true;
    }
}
