package ua.i.mail100.service;

import ua.i.mail100.dao.ItemDAO;
import ua.i.mail100.model.Franchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemDAO itemDAO;

    public Franchise getById(Integer id) {
        Optional<Franchise> cart = itemDAO.findById(id);
        if (cart.isEmpty()) {
            return null;
        }
        return cart.get();
    }

    public List<Franchise> getAllByCart(Integer cartId) {
        return itemDAO.getAllByCart(cartId);
    }

    public List<Franchise> getAllAvailable() {
        return itemDAO.getAllAvailable();
    }

    // CRUD SQL
    public Franchise save(Franchise item) {
        if (item.getId() == null) {
            return itemDAO.save(item);
        }
        return null;
    }

    public Franchise update(Franchise cart) {
        if (cart.getId() != null && itemDAO.getOne(cart.getId()) != null) {
            return itemDAO.save(cart);
        }
        return null;
    }

    public List<Franchise> getAll() {
        return itemDAO.findAll();
    }

    public void delete(Franchise cart) {
        itemDAO.delete(cart);
    }

    public void deleteById(Integer id) throws RuntimeException {
        itemDAO.deleteById(id);
    }

}
