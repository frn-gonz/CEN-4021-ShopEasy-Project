package com.example.shopeasy.DiscountsAndPromo;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {

    @Autowired
    private DiscountsRepository DiscountsRepository;

    @GetMapping
    public List<DiscountCode> getAllDiscounts() {
        return DiscountsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DiscountCode> getDiscountById(@PathVariable Integer id) {
        return DiscountsRepository.findById(id);
    }

    @PostMapping
    public DiscountCode createDiscount(
            @RequestBody DiscountCode discountCode) {

        return DiscountsRepository.save(discountCode);
    }

    @PutMapping("/{id}")
    public DiscountCode updateDiscount(
            @PathVariable Integer id,
            @RequestBody DiscountCode discountCode) {

        discountCode.setCodeId(id);
        return DiscountsRepository.save(discountCode);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable Integer id) {
        DiscountsRepository.deleteById(id);
    }
}