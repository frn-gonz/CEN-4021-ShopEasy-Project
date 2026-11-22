package com.example.shopeasy.ProductAndInventory;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private Integer productId;

    @Column(name = "productname", length = 50)
    private String productName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", precision = 8, scale = 2)
    private BigDecimal price;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "category", length = 50)
    private String category;

    @Lob
    @Column(name = "product_image")
    private byte[] productImage;

    public Product() {
    }

    public Integer getProductId() {

        return productId;
    }

    public void setProductId(Integer productId) {

        this.productId = productId;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public Boolean getAvailability() {

        return availability;
    }

    public void setAvailability(Boolean availability) {

        this.availability = availability;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {

        this.quantity = quantity;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }
}