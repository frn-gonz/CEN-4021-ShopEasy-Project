package com.example.shopeasy.ShoppingCarts;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @GetMapping
    public List<ShoppingCart> getAllCarts() {
        return shoppingCartRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ShoppingCart> getCartById(
            @PathVariable Integer id) {

        return shoppingCartRepository.findById(id);
    }

    @PostMapping
    public ShoppingCart createCart(
            @RequestBody ShoppingCart cart) {

        return shoppingCartRepository.save(cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Integer id) {
        shoppingCartRepository.deleteById(id);
    }
}