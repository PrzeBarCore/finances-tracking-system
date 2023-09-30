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
    private Integer defaultExpenseCategoryId;

    ProductSnapshot(){}
    ProductSnapshot(Integer id, NameString name, Company producer, Double quantity, String unit, Integer productCategoryId, Integer defaultExpenseCategoryId) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        this.unit = unit;
        this.productCategoryId = productCategoryId;
        this.defaultExpenseCategoryId = defaultExpenseCategoryId;
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

    Integer getDefaultExpenseCategoryId(){
        return defaultExpenseCategoryId;
    }
}