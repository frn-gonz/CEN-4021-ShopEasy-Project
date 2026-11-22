package com.example.shopeasy.Services;

import com.example.shopeasy.ProductAndInventory.Product;
import com.example.shopeasy.ProductAndInventory.ProductsRepository;
import com.example.shopeasy.ShoppingCarts.CartItem;
import com.example.shopeasy.ShoppingCarts.CartItemRepository;
import com.example.shopeasy.ShoppingCarts.ShoppingCart;
import com.example.shopeasy.ShoppingCarts.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Everything to do with a user's shopping cart. Screens pass in the
 * logged-in account's ID and never touch the cart repositories directly.
 */
@Service
public class CartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductsRepository productsRepository;

    public CartService(ShoppingCartRepository shoppingCartRepository,
                       CartItemRepository cartItemRepository,
                       ProductsRepository productsRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productsRepository = productsRepository;
    }

    // New accounts (made through Sign Up) don't have a cart until they first need one
    @Transactional
    public ShoppingCart findOrCreateCart(Integer accountId) {
        return shoppingCartRepository.findByAccountId(accountId).orElseGet(() -> {
            ShoppingCart cart = new ShoppingCart();
            cart.setAccountId(accountId);
            cart.setCreatedAt(LocalDateTime.now());
            return shoppingCartRepository.save(cart);
        });
    }

    // The cart's items with each product's name and price
    @Transactional
    public List<CartLine> getItems(Integer accountId) {
        ShoppingCart cart = findOrCreateCart(accountId);
        List<CartLine> lines = new ArrayList<>();
        for (CartItem item : cartItemRepository.findByCartId(cart.getCartId())) {
            productsRepository.findById(item.getProductId()).ifPresent(product ->
                    lines.add(new CartLine(item.getCartItemId(), product.getProductId(),
                            product.getProductName(),
                            Objects.requireNonNullElse(product.getPrice(), BigDecimal.ZERO),
                            quantityOf(item))));
        }
        return lines;
    }

    // Adds to the existing line if the product is already in the cart.
    // Throws Error 321 if the product doesn't have that many in stock.
    @Transactional
    public void addItem(Integer accountId, Integer productId, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        ShoppingCart cart = findOrCreateCart(accountId);
        Product product = productsRepository.findById(productId)
                .orElseThrow(() -> new ShopEasyException(ShopEasyException.OUT_OF_STOCK));

        CartItem item = cartItemRepository.findByCartId(cart.getCartId()).stream()
                .filter(existing -> productId.equals(existing.getProductId()))
                .findFirst()
                .orElse(null);
        int newQuantity = (item == null ? 0 : quantityOf(item)) + quantity;
        if (!hasEnoughStock(product, newQuantity)) {
            throw new ShopEasyException(ShopEasyException.OUT_OF_STOCK);
        }

        if (item == null) {
            item = new CartItem();
            item.setCartId(cart.getCartId());
            item.setProductId(productId);
        }
        item.setQuantity(newQuantity);
        cartItemRepository.save(item);
    }

    // Only removes the item if it belongs to this account's cart
    @Transactional
    public void removeItem(Integer accountId, Integer cartItemId) {
        ShoppingCart cart = findOrCreateCart(accountId);
        cartItemRepository.findById(cartItemId)
                .filter(item -> cart.getCartId().equals(item.getCartId()))
                .ifPresent(cartItemRepository::delete);
    }

    @Transactional
    public void clearCart(Integer accountId) {
        ShoppingCart cart = findOrCreateCart(accountId);
        cartItemRepository.deleteAll(cartItemRepository.findByCartId(cart.getCartId()));
    }

    // Sum of price x quantity for every item, rounded to cents
    public BigDecimal calculateSubtotal(List<CartLine> items) {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartLine item : items) {
            subtotal = subtotal.add(item.lineTotal());
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    // Shared with CheckoutService: a product can be bought only if it's marked
    // available and has at least this many in stock
    static boolean hasEnoughStock(Product product, int quantity) {
        return !Boolean.FALSE.equals(product.getAvailability())
                && product.getQuantity() != null
                && product.getQuantity() >= quantity;
    }

    // cart_items.quantity defaults to 1 in the database
    private static int quantityOf(CartItem item) {
        return item.getQuantity() == null ? 1 : item.getQuantity();
    }
}
