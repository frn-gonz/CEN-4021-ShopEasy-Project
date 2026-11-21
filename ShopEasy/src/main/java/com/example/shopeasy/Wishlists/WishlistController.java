package com.example.shopeasy.Wishlists;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Wishlist> getWishlistById(
            @PathVariable Integer id) {

        return wishlistRepository.findById(id);
    }

    @PostMapping
    public Wishlist createWishlist(
            @RequestBody Wishlist wishlist) {

        return wishlistRepository.save(wishlist);
    }

    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable Integer id) {
        wishlistRepository.deleteById(id);
    }
}