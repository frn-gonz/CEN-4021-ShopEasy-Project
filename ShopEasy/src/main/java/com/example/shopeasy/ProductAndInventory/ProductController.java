package com.example.shopeasy.ProductAndInventory;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsRepository ProductsRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return ProductsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return ProductsRepository.findById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return ProductsRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Integer id,
            @RequestBody Product updatedProduct) {

        updatedProduct.setProductId(id);
        return ProductsRepository.save(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        ProductsRepository.deleteById(id);
    }
}