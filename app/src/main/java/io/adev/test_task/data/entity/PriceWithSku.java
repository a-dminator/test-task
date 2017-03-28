package io.adev.test_task.data.entity;

public class PriceWithSku {
    public final String sku;
    public final float price;
    public PriceWithSku(String sku, float price) {
        this.sku = sku;
        this.price = price;
    }
}
