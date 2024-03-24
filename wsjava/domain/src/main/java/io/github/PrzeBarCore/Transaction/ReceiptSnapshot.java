package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.List;

class ReceiptSnapshot {
    private Integer id;
    private MonetaryAmount totalDiscount;
    private List<ReceiptItemSnapshot> items;

    ReceiptSnapshot(){}
    ReceiptSnapshot(Integer id, MonetaryAmount totalDiscount, List<ReceiptItemSnapshot> items) {
        this.id = id;
        this.totalDiscount = totalDiscount;
        this.items = items;
    }

    Integer getId() {
        return id;
    }

    MonetaryAmount getTotalDiscount() { return totalDiscount; }

    List<ReceiptItemSnapshot> getItems() {
        return items;
    }
}

class ReceiptItemSnapshot {
    private Integer id;
    private NameString name;
    private Double quantity;
    private MonetaryAmount regularPrice;
    private MonetaryAmount discount;
    private Integer productId;
    private Integer expenseCategoryId;

    ReceiptItemSnapshot(){}
    ReceiptItemSnapshot(Integer id, NameString name, Double quantity, MonetaryAmount regularPrice, MonetaryAmount discount, Integer productId, Integer expenseCategoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.regularPrice = regularPrice;
        this.discount = discount;
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
    Integer getProductId() {
        return productId;
    }

    Integer getExpenseCategoryId() {
        return expenseCategoryId;
    }
}
