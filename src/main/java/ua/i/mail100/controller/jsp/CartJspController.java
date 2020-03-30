package ua.i.mail100.controller.jsp;

import ua.i.mail100.dao.dto.OrderDTO;
import ua.i.mail100.model.Cart;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.User;
import ua.i.mail100.service.CartService;
import ua.i.mail100.service.ItemService;
import ua.i.mail100.service.OrderService;
import ua.i.mail100.service.UserService;
import ua.i.mail100.util.MapperOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("cart")
public class CartJspController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    MapperOrderUtil mapperOrderUtil;

    @GetMapping("all-by-user")
    public String getAllByUser(Model model,
                               @RequestParam(value = "userId") String userId) {
        Integer userIdSelected = Integer.valueOf(userId);
        User user = userService.getById(userIdSelected);
        if (user == null) {
            model.addAttribute("message", "User is wrong! Relogin, pls");
            return "authorization";
        }

        List<Cart> carts = cartService.getAllByUser(userIdSelected);
        model.addAttribute("cartCollection", carts);
        model.addAttribute("user", user);
        return "carts";
    }

    @PostMapping("open")
    public String getCard(Model model,
                                  @RequestParam(value = "userId") String userId,
                                  @RequestParam(value = "cartId") String cartId) {
        Integer userIdSelected = Integer.valueOf(userId);
        User user = userService.getById(userIdSelected);
        if (user == null) {
            model.addAttribute("message", "User is wrong! Relogin, pls");
            return "authorization";
        }

        Integer cartIdSelected = Integer.valueOf(cartId);
        Cart cart = cartService.getById(cartIdSelected);
        if (cart == null) {
            model.addAttribute("user", user);
            List<Franchise> items = itemService.getAllAvailable();
            model.addAttribute("itemCollection", items);
            model.addAttribute("message", "Cart is wrong!");
            return "user-cabinet";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        List<OrderDTO> orderDTOS = mapperOrderUtil.toOrderDTOListFromOrderList(orderService.getAllByCart(cartIdSelected));
        model.addAttribute("orderDTOCollection", orderDTOS);
        return "cart";
    }


    @PostMapping("do-cart-to-be-closed")
    public String cartDoToBeClosed(Model model,
                                   @RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "cartId") String cartId) {
        Integer userIdSelected = Integer.valueOf(userId);
        User user = userService.getById(userIdSelected);
        if (user == null) {
            model.addAttribute("message", "User is wrong! Relogin, pls");
            return "authorization";
        }

        Integer cartIdSelected = Integer.valueOf(cartId);
        Cart cart = cartService.getById(cartIdSelected);
        if (cart == null) {
            model.addAttribute("user", user);
            List<Franchise> items = itemService.getAllAvailable();
            model.addAttribute("itemCollection", items);
            model.addAttribute("message", "Cart is wrong!");
            return "user-cabinet";
        }

        cartService.updateStatus(cartIdSelected, Status.TO_BE_CLOSED);

        List<OrderDTO> orderDTOS = mapperOrderUtil.toOrderDTOListFromOrderList(orderService.getAllByCart(cartIdSelected));
        model.addAttribute("orderDTOCollection", orderDTOS);
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "cart";
    }


    @PostMapping("do-cart-closed")
    public String cartDoClosed(Model model,
                               @RequestParam(value = "userId") String userId,
                               @RequestParam(value = "cartId") String cartId) {
        Integer userIdSelected = Integer.valueOf(userId);
        User user = userService.getById(userIdSelected);
        if (user == null) {
            model.addAttribute("message", "User is wrong! Relogin, pls");
            return "authorization";
        }

        Integer cartIdSelected = Integer.valueOf(cartId);
        Cart cart = cartService.getById(cartIdSelected);
        if (cart == null) {
            model.addAttribute("user", user);
            List<Franchise> items = itemService.getAllAvailable();
            model.addAttribute("itemCollection", items);
            model.addAttribute("message", "Cart is wrong!");
            return "user-cabinet";
        }

        cartService.updateStatus(cartIdSelected, Status.CLOSED);

        List<OrderDTO> orderDTOS = mapperOrderUtil.toOrderDTOListFromOrderList(orderService.getAllByCart(cartIdSelected));
        model.addAttribute("orderDTOCollection", orderDTOS);
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "cart";
    }
}
