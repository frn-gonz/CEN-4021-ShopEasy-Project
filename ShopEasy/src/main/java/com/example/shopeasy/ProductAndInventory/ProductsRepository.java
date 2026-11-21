package com.example.shopeasy.ProductAndInventory;

import com.example.shopeasy.ProductAndInventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
