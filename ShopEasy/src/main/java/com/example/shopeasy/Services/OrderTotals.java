package com.example.shopeasy.Services;

import java.math.BigDecimal;

/**
 * The money lines of an order, all rounded to cents:
 * total = subtotal - discount + tax.
 */
public record OrderTotals(BigDecimal subtotal, BigDecimal discount, BigDecimal tax, BigDecimal total) {
}
