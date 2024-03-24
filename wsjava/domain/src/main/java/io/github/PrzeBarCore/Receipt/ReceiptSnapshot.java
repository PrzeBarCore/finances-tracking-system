package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;

import java.time.LocalDateTime;
import java.util.List;

class ReceiptSnapshot {
    private Integer id;
    private MonetaryAmount totalDiscount;
    private Integer relatedTransactionId;
    private List<ReceiptItemSnapshot> items;

    ReceiptSnapshot(){}
    ReceiptSnapshot(Integer id, MonetaryAmount totalDiscount, Integer relatedTransactionId, List<ReceiptItemSnapshot> items) {
        this.id = id;
        this.totalDiscount = totalDiscount;
        this.relatedTransactionId = relatedTransactionId;
        this.items = items;
    }

    Integer getId() {
        return id;
    }

    MonetaryAmount getTotalDiscount() { return totalDiscount; }

    Integer getRelatedTransactionId() { return relatedTransactionId; }

    List<ReceiptItemSnapshot> getItems() {
        return items;
    }
}
