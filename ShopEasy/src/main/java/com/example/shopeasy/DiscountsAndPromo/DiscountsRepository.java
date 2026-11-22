package com.example.shopeasy.DiscountsAndPromo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountsRepository  extends JpaRepository<DiscountCode, Integer> {

    // A List because codename isn't unique in the database
    List<DiscountCode> findByCodeName(String codeName);
}
