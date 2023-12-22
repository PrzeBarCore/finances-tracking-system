package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

class ReceiptItemSnapshot {
    private Integer id;
    private NameString name;
    private Integer productId;
    private Double quantity;
    private MonetaryAmount regularPrice;
    private MonetaryAmount discount;
    private Integer expenseCategoryId;

    ReceiptItemSnapshot(){}
    ReceiptItemSnapshot(Integer id, NameString name, Integer productId, Double quantity, MonetaryAmount regularPrice, MonetaryAmount discount, Integer expenseCategoryId) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
        this.regularPrice = regularPrice;
        this.discount = discount;
        this.expenseCategoryId = expenseCategoryId;
    }

    Integer getId() {
        return id;
    }

    NameString getName() {
        return name;
    }

    Integer getProductId() {
        return productId;
    }

    Double getQuantity() {
        return quantity;
    }

    MonetaryAmount getRegularPrice() {
        return regularPrice;
    }

    MonetaryAmount getDiscount() {
        return discount;
    }

    Integer getExpenseCategoryId() {
        return expenseCategoryId;
    }
}
