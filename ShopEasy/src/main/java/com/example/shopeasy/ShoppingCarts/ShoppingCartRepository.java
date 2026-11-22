package com.example.shopeasy.ShoppingCarts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository
        extends JpaRepository<ShoppingCart, Integer> {

    // Each account has at most one cart (shopping_cart.accountID is unique)
    Optional<ShoppingCart> findByAccountId(Integer accountId);
}