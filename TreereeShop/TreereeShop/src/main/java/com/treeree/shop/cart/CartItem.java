package com.treeree.shop.cart;

import java.math.BigDecimal;

public class CartItem {
    private Long productId;
    private String name;
    private String slug;
    private BigDecimal price;
    private int quantity;

    public CartItem() {}
    public CartItem(Long productId, String name, String slug, BigDecimal price, int quantity) {
        this.productId = productId; this.name = name; this.slug = slug; this.price = price; this.quantity = quantity;
    }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getLineTotal() { return price.multiply(BigDecimal.valueOf(quantity)); }
}