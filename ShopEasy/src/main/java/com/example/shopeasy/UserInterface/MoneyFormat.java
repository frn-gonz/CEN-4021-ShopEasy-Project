package com.example.shopeasy.UserInterface;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

// Shows BigDecimal amounts as US dollars, e.g. 1299.9 -> "$1,299.90"
final class MoneyFormat {

    private MoneyFormat() {
    }

    static String format(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount);
    }

    // Shows a stored rate as a percent, e.g. 0.15 -> "15%"
    static String percent(BigDecimal fraction) {
        return fraction.movePointRight(2).stripTrailingZeros().toPlainString() + "%";
    }
}
