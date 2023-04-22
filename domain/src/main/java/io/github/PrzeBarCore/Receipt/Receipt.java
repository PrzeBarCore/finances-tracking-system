package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class Receipt {
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private boolean isContainingListOfItems;
    private List<ReceiptItem> items;

    class ReceiptItem {
        private Integer id;
        private String name;
        private Integer productId;
        private Double quantity;
        private MonetaryAmount regularPrice;
        private MonetaryAmount discount;
        //transactionCategory
        private Integer productCategory;
    }
}
