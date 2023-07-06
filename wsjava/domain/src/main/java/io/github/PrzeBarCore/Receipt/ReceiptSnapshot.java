package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;

import java.time.LocalDateTime;
import java.util.List;

class ReceiptSnapshot {
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private boolean isContainingListOfItems;
    private List<ReceiptItemSnapshot> items;

    ReceiptSnapshot(){}
    ReceiptSnapshot(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, boolean isContainingListOfItems, List<ReceiptItemSnapshot> items) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.isContainingListOfItems = isContainingListOfItems;
        this.items = items;
    }

    Integer getId() {
        return id;
    }

    LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    MonetaryAmount getTotalValue() {
        return totalValue;
    }

    boolean isContainingListOfItems() {
        return isContainingListOfItems;
    }

    List<ReceiptItemSnapshot> getItems() {
        return items;
    }
}
