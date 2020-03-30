package ua.i.mail100.dao;

import ua.i.mail100.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDAO extends JpaRepository<Franchise, Integer> {
    @Query(nativeQuery = true, value = "SELECT i.* FROM items i JOIN orders o ON o.item_id = i.id JOIN carts c ON c.id = o.cart_id WHERE c.id = :cartId")
    List<Franchise> getAllByCart(@Param("cartId") Integer cartId);

    @Query(nativeQuery = true, value = "SELECT * FROM items WHERE availability > 0")
    List<Franchise> getAllAvailable();
}