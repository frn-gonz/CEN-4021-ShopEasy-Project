package com.example.shopeasy.DiscountsAndPromo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountsRepository  extends JpaRepository<DiscountCode, Integer> {
}
