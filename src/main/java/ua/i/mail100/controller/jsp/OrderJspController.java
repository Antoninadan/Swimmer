package ua.i.mail100.controller.jsp;

import ua.i.mail100.model.Cart;
import ua.i.mail100.model.Franchise;
import ua.i.mail100.model.Order;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Profile("jsp")
@RequestMapping("order")
public class OrderJspController {
    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    MapperOrderUtil mapperOrderUtil;

    @PostMapping("add-item-in-cart")
    public String addItemInCart(Model model,
                                @RequestParam(value = "itemId") String itemId,
                                @RequestParam(value = "userId") String userId) {
        Integer userIdSelected = Integer.valueOf(userId);
        User user = userService.getById(userIdSelected);
        if (user == null) {
            model.addAttribute("message", "User is wrong! Relogin, pls");
            return "authorization";
        }

        Integer itemIdSelected = Integer.valueOf(itemId);
        Franchise item = itemService.getById(itemIdSelected);
        if (item == null) {
            model.addAttribute("user", user);
            List<Franchise> items = itemService.getAllAvailable();
            model.addAttribute("itemCollection", items);
            model.addAttribute("message", "Franchise is wrong!");
            return "user-cabinet";
        }

        Order order = orderService.addItemInCart(userIdSelected, itemIdSelected);
        Cart cart = cartService.getById(order.getCart().getId());

        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        List<Franchise> items = itemService.getAllAvailable();
        model.addAttribute("itemCollection", items);
        return "user-cabinet";
    }

}
