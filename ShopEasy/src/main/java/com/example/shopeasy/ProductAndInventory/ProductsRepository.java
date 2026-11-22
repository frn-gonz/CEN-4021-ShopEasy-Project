package com.example.shopeasy.ProductAndInventory;

import com.example.shopeasy.ProductAndInventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameContainingIgnoreCase(
            String productName
    );
}
