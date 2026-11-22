package com.example.shopeasy.Services;

import com.example.shopeasy.DiscountsAndPromo.DiscountCode;
import com.example.shopeasy.DiscountsAndPromo.DiscountsRepository;
import com.example.shopeasy.ProductAndInventory.Product;
import com.example.shopeasy.ProductAndInventory.ProductsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The checkout rules: validating payment details and discount codes,
 * working out the totals, and placing the order.
 */
@Service
public class CheckoutService {

    public static final BigDecimal SALES_TAX_RATE = new BigDecimal("0.07");

    private final CartService cartService;
    private final ProductsRepository productsRepository;
    private final DiscountsRepository discountsRepository;

    public CheckoutService(CartService cartService,
                           ProductsRepository productsRepository,
                           DiscountsRepository discountsRepository) {
        this.cartService = cartService;
        this.productsRepository = productsRepository;
        this.discountsRepository = discountsRepository;
    }

    // A blank code means "no discount" and returns null. Otherwise the code must exist
    // and not be past its expiration date (it is still valid on that date), else Error 205.
    @Transactional(readOnly = true)
    public DiscountCode findValidDiscount(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        LocalDate today = LocalDate.now();
        return discountsRepository.findByCodeName(code.trim()).stream()
                .filter(discount -> discount.getExpirationDate() == null
                        || !discount.getExpirationDate().isBefore(today))
                .findFirst()
                .orElseThrow(() -> new ShopEasyException(ShopEasyException.INVALID_DISCOUNT));
    }

    // Total = subtotal - discount + 7% sales tax, where the tax is on the discounted amount.
    // The discount column stores a fraction (0.15 means 15% off), so it is multiplied, not divided by 100.
    public OrderTotals calculateTotals(BigDecimal subtotal, DiscountCode discountCode) {
        BigDecimal rate = discountCode == null || discountCode.getDiscount() == null
                ? BigDecimal.ZERO
                : discountCode.getDiscount();
        BigDecimal discount = toCents(subtotal.multiply(rate));
        BigDecimal afterDiscount = subtotal.subtract(discount);
        BigDecimal tax = toCents(afterDiscount.multiply(SALES_TAX_RATE));
        return new OrderTotals(toCents(subtotal), discount, tax, toCents(afterDiscount.add(tax)));
    }

    // Everything here runs in one transaction. Every check happens before anything is
    // changed, and any ShopEasyException rolls the whole order back.
    @Transactional
    public OrderReceipt placeOrder(Integer accountId, String cardNumber, String shippingAddress,
                                   String discountCodeText) {
        String cardDigits = validateCardNumber(cardNumber);
        validateShippingAddress(shippingAddress);
        DiscountCode discountCode = findValidDiscount(discountCodeText);

        List<CartLine> items = cartService.getItems(accountId);
        if (items.isEmpty()) {
            throw new ShopEasyException(ShopEasyException.EMPTY_CART);
        }

        // How many of each product the order needs
        Map<Integer, Integer> needed = new LinkedHashMap<>();
        for (CartLine item : items) {
            needed.merge(item.productId(), item.quantity(), Integer::sum);
        }

        // Re-check stock for every product before changing anything
        List<Product> products = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : needed.entrySet()) {
            Product product = productsRepository.findById(entry.getKey())
                    .orElseThrow(() -> new ShopEasyException(ShopEasyException.OUT_OF_STOCK));
            if (!CartService.hasEnoughStock(product, entry.getValue())) {
                throw new ShopEasyException(ShopEasyException.OUT_OF_STOCK);
            }
            products.add(product);
        }

        // All in stock: take the items out of inventory
        for (Product product : products) {
            int remaining = product.getQuantity() - needed.get(product.getProductId());
            product.setQuantity(remaining);
            if (remaining == 0) {
                product.setAvailability(false);
            }
            productsRepository.save(product);
        }

        cartService.clearCart(accountId);

        OrderTotals totals = calculateTotals(cartService.calculateSubtotal(items), discountCode);
        return new OrderReceipt(items, discountCode == null ? null : discountCode.getCodeName(),
                totals, shippingAddress.trim(), cardDigits.substring(12));
    }

    // Must be 16 digits; spaces or dashes between groups are allowed. Returns just the digits.
    private String validateCardNumber(String cardNumber) {
        String digits = cardNumber == null ? "" : cardNumber.replaceAll("[\\s-]", "");
        if (!digits.matches("\\d{16}")) {
            throw new ShopEasyException(ShopEasyException.INVALID_CARD);
        }
        return digits;
    }

    private void validateShippingAddress(String shippingAddress) {
        if (shippingAddress == null || shippingAddress.isBlank()) {
            throw new ShopEasyException(ShopEasyException.INVALID_ADDRESS);
        }
    }

    private static BigDecimal toCents(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
