//package ua.i.mail100.presenter.dto;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ua.i.mail100.model.User;
//import ua.i.mail100.service.UserService;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class MapperUserUtil {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    public User toUser(UserDTO userDTO) {
//        User user = new User();
//        user.setId(userDTO.getId());
//        return user;
//    }
//
//    public UserDTO toUserDTO(String request) {
//        try {
//            return objectMapper.readValue(request, UserDTO.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public CartDTO toCartDTOFromCart(Cart cart) {
//        CartDTO cartDTO = new CartDTO();
//        cartDTO.setId(cart.getId());
//        cartDTO.setUserId(cart.getUser().getId());
//        cartDTO.setCreationTime(cart.getCreationTime());
//        cartDTO.setStatus(cart.getStatus().name());
//        return cartDTO;
//    }
//
//    public List<CartDTO> toCartDTOListFromCartList(List<Cart> carts) {
//        List<CartDTO> cartDTOs = new ArrayList<>();
//        for(Cart each:carts){
//            CartDTO cartDTO = toCartDTOFromCart(each);
//            cartDTOs.add(cartDTO);
//        }
//        return cartDTOs;
//    }
//}
