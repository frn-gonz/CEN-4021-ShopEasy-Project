package com.example.shopeasy.Services;

import com.example.shopeasy.ProductAndInventory.ProductsRepository;
import com.example.shopeasy.Wishlists.Wishlist;
import com.example.shopeasy.Wishlists.WishlistItem;
import com.example.shopeasy.Wishlists.WishlistItemRepository;
import com.example.shopeasy.Wishlists.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Everything to do with a user's wish list, following the same pattern as CartService:
 * screens pass in the logged-in account's ID and never touch the wish list repositories.
 */
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final ProductsRepository productsRepository;
    private final CartService cartService;

    public WishlistService(WishlistRepository wishlistRepository,
                           WishlistItemRepository wishlistItemRepository,
                           ProductsRepository productsRepository,
                           CartService cartService) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.productsRepository = productsRepository;
        this.cartService = cartService;
    }

    // New accounts (made through Sign Up) don't have a wish list until they first need one
    @Transactional
    public Wishlist findOrCreateWishlist(Integer accountId) {
        return wishlistRepository.findByAccountId(accountId).orElseGet(() -> {
            Wishlist wishlist = new Wishlist();
            wishlist.setAccountId(accountId);
            wishlist.setCreatedAt(LocalDateTime.now());
            return wishlistRepository.save(wishlist);
        });
    }

    // The wish list's items with each product's name and price
    @Transactional
    public List<WishlistLine> getItems(Integer accountId) {
        Wishlist wishlist = findOrCreateWishlist(accountId);
        List<WishlistLine> lines = new ArrayList<>();
        for (WishlistItem item : wishlistItemRepository.findByWishlistId(wishlist.getWishlistId())) {
            productsRepository.findById(item.getProductId()).ifPresent(product ->
                    lines.add(new WishlistLine(item.getWishlistItemId(), product.getProductId(),
                            product.getProductName(),
                            Objects.requireNonNullElse(product.getPrice(), BigDecimal.ZERO))));
        }
        return lines;
    }

    // Used by Inventory's "Add To Wishlist" button. Returns false (and adds nothing) when the
    // product is already on the list or doesn't exist, so the screen can say so.
    @Transactional
    public boolean addItem(Integer accountId, Integer productId) {
        Wishlist wishlist = findOrCreateWishlist(accountId);
        boolean alreadyThere = wishlistItemRepository.findByWishlistId(wishlist.getWishlistId()).stream()
                .anyMatch(item -> productId.equals(item.getProductId()));
        if (alreadyThere || productsRepository.findById(productId).isEmpty()) {
            return false;
        }
        WishlistItem item = new WishlistItem();
        item.setWishlistId(wishlist.getWishlistId());
        item.setProductId(productId);
        wishlistItemRepository.save(item);
        return true;
    }

    // Only removes the item if it belongs to this account's wish list
    @Transactional
    public void removeItem(Integer accountId, Integer wishlistItemId) {
        findOwnItem(accountId, wishlistItemId).ifPresent(wishlistItemRepository::delete);
    }

    // Adds one of the product to the cart, then takes it off the wish list, in one transaction.
    // If the product is out of stock, CartService throws Error 321 and the wish list stays as it was.
    @Transactional
    public void moveToCart(Integer accountId, Integer wishlistItemId) {
        Optional<WishlistItem> item = findOwnItem(accountId, wishlistItemId);
        if (item.isEmpty()) {
            return;
        }
        cartService.addItem(accountId, item.get().getProductId(), 1);
        wishlistItemRepository.delete(item.get());
    }

    private Optional<WishlistItem> findOwnItem(Integer accountId, Integer wishlistItemId) {
        Wishlist wishlist = findOrCreateWishlist(accountId);
        return wishlistItemRepository.findById(wishlistItemId)
                .filter(item -> wishlist.getWishlistId().equals(item.getWishlistId()));
    }
}
