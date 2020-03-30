package ua.i.mail100.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.i.mail100.dao.dto.CartDTO;
import ua.i.mail100.model.Cart;
import ua.i.mail100.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperCartUtil {
    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setStatus(Status.valueOf(cartDTO.getStatus().toUpperCase()));
        cart.setUser(userService.getById(cartDTO.getUserId()));
        cart.setCreationTime(cartDTO.getCreationTime());
        return cart;
    }

    public CartDTO toCartDTO(String request) {
        try {
            return objectMapper.readValue(request, CartDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CartDTO toCartDTOFromCart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        cartDTO.setCreationTime(cart.getCreationTime());
        cartDTO.setStatus(cart.getStatus().name());
        return cartDTO;
    }

    public List<CartDTO> toCartDTOListFromCartList(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        for(Cart each:carts){
            CartDTO cartDTO = toCartDTOFromCart(each);
            cartDTOs.add(cartDTO);
        }
        return cartDTOs;
    }
}
