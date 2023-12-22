package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

class Receipt {
    static Receipt restore(ReceiptSnapshot snapshot) {
        return new Receipt(snapshot.getId(),
                snapshot.getIssuedOnDateTime(),
                snapshot.getTotalValue(),
                snapshot.isContainingListOfItems(),
                snapshot.getItems()
                        .stream()
                        .map(ReceiptItem::restore)
                        .collect(Collectors.toList()));
    }
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private boolean isContainingListOfItems;
    private List<ReceiptItem> items;

    private Receipt(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, boolean isContainingListOfItems, List<ReceiptItem> items) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.isContainingListOfItems = isContainingListOfItems;
        this.items = items;
    }

    ReceiptSnapshot getSnapshot(){
        return new ReceiptSnapshot(this.id,
                this.issuedOnDateTime,
                this.totalValue,
                this.isContainingListOfItems,
                this.items.stream()
                        .map(ReceiptItem::getItemSnapshot)
                        .collect(Collectors.toList()));
    }

     static class ReceiptItem {
        static private ReceiptItem restore(ReceiptItemSnapshot snapshot){
            return new ReceiptItem(snapshot.getId(),
                    snapshot.getName(),
                    snapshot.getProductId(),
                    snapshot.getQuantity(),
                    snapshot.getRegularPrice(),
                    snapshot.getDiscount(),
                    snapshot.getExpenseCategoryId());
        }
        private Integer id;
        private NameString name;
        private Integer productId;
        private Double quantity;
        private MonetaryAmount regularPrice;
        private MonetaryAmount discount;
        private Integer expenseCategoryId;

         private ReceiptItemSnapshot getItemSnapshot(){
            return new ReceiptItemSnapshot(this.id,
                    this.name,
                    this.productId,
                    this.quantity,
                    this.regularPrice,
                    this.discount,
                    this.expenseCategoryId);
        }

         private ReceiptItem(Integer id, NameString name, Integer productId, Double quantity, MonetaryAmount regularPrice, MonetaryAmount discount, Integer expenseCategoryId) {
             this.id = id;
             this.name = name;
             this.productId = productId;
             this.quantity = quantity;
             this.regularPrice = regularPrice;
             this.discount = discount;
             this.expenseCategoryId = expenseCategoryId;
         }
     }
}
