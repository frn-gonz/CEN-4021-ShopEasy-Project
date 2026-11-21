package com.example.shopeasy.Wishlists;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist_items")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlistItemID")
    private Integer wishlistItemId;

    @Column(name = "wishlistID")
    private Integer wishlistId;

    @Column(name = "productID")
    private Integer productId;

    public WishlistItem() {
    }

    public Integer getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(Integer wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}