package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

class ProductSnapshot {
    private Integer id;
    private NameString name;
    private Company producer;
    private Double quantity;
    private String unit;
    private Integer productCategoryId;
    private Double defaultPrice;
    private Integer defaultReceiptTransactionCategoryId;

    ProductSnapshot(){}
    ProductSnapshot(Integer id, NameString name, Company producer, Double quantity, String unit, Integer productCategoryId,  Double defaultPrice, Integer defaultReceiptTransactionCategoryId) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        this.unit = unit;
        this.productCategoryId = productCategoryId;
        this.defaultPrice =defaultPrice;
        this.defaultReceiptTransactionCategoryId = defaultReceiptTransactionCategoryId;
    }

    Integer getId() {
        return id;
    }

    NameString getName() {
        return name;
    }

    Company getProducer() {
        return producer;
    }

    Double getQuantity() {
        return quantity;
    }

    String getUnit() {
        return unit;
    }

    Integer getProductCategoryId() {
        return productCategoryId;
    }

    Double getDefaultPrice() {
        return defaultPrice;
    }

    Integer getDefaultReceiptTransactionCategoryId() {
        return defaultReceiptTransactionCategoryId;
    }
}
