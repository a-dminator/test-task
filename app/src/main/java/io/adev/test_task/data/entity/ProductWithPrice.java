package io.adev.test_task.data.entity;

public class ProductWithPrice {
    public final Product product;
    public final float price;
    public ProductWithPrice(Product product, float price) {
        this.product = product;
        this.price = price;
    }
}
