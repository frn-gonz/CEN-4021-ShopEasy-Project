package com.example.shopeasy.Services;

import java.util.List;

/**
 * What a placed order contained, for the order confirmation screen.
 * discountCode is null when no code was used. Only the card's last four
 * digits are kept.
 */
public record OrderReceipt(List<CartLine> items, String discountCode, OrderTotals totals,
                           String shippingAddress, String cardLastFour) {
}
