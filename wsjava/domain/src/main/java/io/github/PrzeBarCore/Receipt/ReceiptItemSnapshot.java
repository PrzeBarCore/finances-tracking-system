package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

class ReceiptItemSnapshot {
    private Integer id;
    private NameString name;
    private Double quantity;
    private MonetaryAmount regularPrice;
    private MonetaryAmount discount;
    private Integer receiptId;
    private Integer productId;
    private Integer expenseCategoryId;

    ReceiptItemSnapshot(){}
    ReceiptItemSnapshot(Integer id, NameString name, Double quantity, MonetaryAmount regularPrice, MonetaryAmount discount, Integer receiptId, Integer productId, Integer expenseCategoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.regularPrice = regularPrice;
        this.discount = discount;
        this.receiptId = receiptId;
        this.productId = productId;
        this.expenseCategoryId = expenseCategoryId;
    }

    Integer getId() {
        return id;
    }

    NameString getName() {
        return name;
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

    Integer getReceiptId() {
        return receiptId;
    }

    Integer getProductId() {
        return productId;
    }

    Integer getExpenseCategoryId() {
        return expenseCategoryId;
    }
}
