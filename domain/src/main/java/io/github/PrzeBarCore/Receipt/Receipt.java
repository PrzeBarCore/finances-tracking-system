package io.github.PrzeBarCore.Receipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class Receipt {
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private BigDecimal totalValue;
    private boolean isContainingListOfItems;
    private List<ReceiptItem> items;

    class ReceiptItem {
        private Integer id;
        private Integer sourceId;
    }
}
