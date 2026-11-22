package com.example.shopeasy.Services;

/**
 * A problem the shopper can fix, such as a mistyped card number or an item that sold out.
 * The services throw it with one of the messages below, and the screens show that
 * message as is. Throwing it inside a @Transactional method also undoes the changes.
 */
public class ShopEasyException extends RuntimeException {

    public static final String INVALID_CARD = "Invalid card number (Error 203)";
    public static final String INVALID_ADDRESS = "Invalid shipping address (Error 204)";
    public static final String INVALID_DISCOUNT = "Invalid or expired discount code (Error 205)";
    public static final String OUT_OF_STOCK = "Item out of stock (Error 321)";
    public static final String EMPTY_CART = "Your cart is empty (Error 322)";

    public ShopEasyException(String message) {
        super(message);
    }
}
