package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

class Product {
    static Product restore(ProductSnapshot snapshot){
        return new Product(
                snapshot.getId(),
                snapshot.getName(),
                snapshot.getProducer(),
                snapshot.getQuantity(),
                snapshot.getUnit(),
                snapshot.getProductCategoryId(),
                snapshot.getDefaultExpenseCategoryId()
        );
    }
    private Integer id;
    private NameString name;
    private Company producer;
    private Double quantity;
    private String unit;
    private Integer productCategoryId;
    private Integer defaultExpenseCategoryId;

    private Product(Integer id, NameString name, Company producer, Double quantity, String unit, Integer productCategoryId, Integer defaultExpenseCategoryId) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        this.unit = unit;
        this.productCategoryId = productCategoryId;
        this.defaultExpenseCategoryId = defaultExpenseCategoryId;
    }

    ProductSnapshot getSnapshot(){
        return new ProductSnapshot(this.id,this.name,this.producer,this.quantity,this.unit, this.productCategoryId, this.defaultExpenseCategoryId);
    }

}
