package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.List;
import java.util.stream.Collectors;

class Receipt {
    static Receipt restore(ReceiptSnapshot snapshot) {
        return new Receipt(snapshot.getId(),
                snapshot.getTotalDiscount(),
                snapshot.getItems()
                        .stream()
                        .map(ReceiptItem::restore)
                        .collect(Collectors.toList()));
    }
    private Integer id;
    private MonetaryAmount totalDiscount;
    private List<ReceiptItem> items;

    private Receipt(Integer id, MonetaryAmount totalDiscount, List<ReceiptItem> items) {
        this.id = id;
        this.totalDiscount = totalDiscount;
        this.items = items;
    }

    ReceiptSnapshot getSnapshot(){
        return new ReceiptSnapshot(this.id,
                this.totalDiscount,
                this.items.stream()
                        .map(ReceiptItem::getItemSnapshot)
                        .collect(Collectors.toList()));
    }

     static class ReceiptItem {
        static private ReceiptItem restore(ReceiptItemSnapshot snapshot){
            return new ReceiptItem(snapshot.getId(),
                    snapshot.getName(),
                    snapshot.getQuantity(),
                    snapshot.getRegularPrice(),
                    snapshot.getDiscount(),
                    snapshot.getProductId(),
                    snapshot.getExpenseCategoryId());
        }

        private Integer id;
        private NameString name;
        private Double quantity;
        private MonetaryAmount regularPrice;
        private MonetaryAmount discount;
        private Integer productId;
        private Integer expenseCategoryId;

         private ReceiptItemSnapshot getItemSnapshot(){
            return new ReceiptItemSnapshot(this.id,
                    this.name,
                    this.quantity,
                    this.regularPrice,
                    this.discount,
                    this.productId,
                    this.expenseCategoryId);
        }

         private ReceiptItem(Integer id, NameString name, Double quantity, MonetaryAmount regularPrice, MonetaryAmount discount, Integer productId, Integer expenseCategoryId) {
             this.id = id;
             this.name = name;
             this.quantity = quantity;
             this.regularPrice = regularPrice;
             this.discount = discount;
             this.productId = productId;
             this.expenseCategoryId = expenseCategoryId;
         }
     }
}
