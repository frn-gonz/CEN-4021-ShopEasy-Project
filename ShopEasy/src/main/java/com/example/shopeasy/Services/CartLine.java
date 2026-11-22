package com.example.shopeasy.Services;

import java.math.BigDecimal;

/**
 * One item in a cart, combined with its product's name and price,
 * so the screens can show it without looking anything up themselves.
 */
public record CartLine(Integer cartItemId, Integer productId, String productName,
                       BigDecimal price, int quantity) {

    public BigDecimal lineTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
