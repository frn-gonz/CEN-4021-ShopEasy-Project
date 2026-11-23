package com.example.shopeasy.Services;

import java.math.BigDecimal;

/**
 * One item on a wish list, combined with its product's name and price,
 * so the Wish List screen can show it without looking anything up itself.
 */
public record WishlistLine(Integer wishlistItemId, Integer productId, String productName, BigDecimal price) {
}
