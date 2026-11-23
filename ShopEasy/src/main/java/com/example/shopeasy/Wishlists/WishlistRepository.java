package com.example.shopeasy.Wishlists;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    // Each account has at most one wish list (wishlist.accountID is unique)
    Optional<Wishlist> findByAccountId(Integer accountId);
}