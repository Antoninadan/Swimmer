package ua.i.mail100.controller.rest;

import ua.i.mail100.model.Franchise;
import ua.i.mail100.service.CartService;
import ua.i.mail100.service.ItemService;
import ua.i.mail100.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
@Slf4j
@Profile("rest")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @PutMapping
    public ResponseEntity save(@RequestBody Franchise item) {
        Franchise savedItem = itemService.save(item);
        if (savedItem == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Franchise item) {
        Franchise updatedItem = itemService.update(item);
        if (updatedItem == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @GetMapping({"", "{id}"})
    public ResponseEntity getItem(@PathVariable(required = false) Integer id) {
        if (id != null) {
            Franchise item = itemService.getById(id);
            if (item == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(item, HttpStatus.OK);
        } else {
            return new ResponseEntity(itemService.getAll(), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Franchise item) {
        try {
            itemService.delete(item);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Bad item params");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        try {
            itemService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("Wrong id = %d", id));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("by-cart/{cartId}")
    public ResponseEntity getAllByCart(@PathVariable Integer cartId) {
        return new ResponseEntity(itemService.getAllByCart(cartId), HttpStatus.OK);
    }

    @GetMapping("available")
    public ResponseEntity getAllAvailable() {
        return new ResponseEntity(itemService.getAllAvailable(), HttpStatus.OK);
    }
}