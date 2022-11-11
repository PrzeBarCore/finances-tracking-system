package io.github.PrzeBarCore.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class TransactionSnapshot {
    private int id;
    private LocalDateTime issuedOnDateTime;
    private BigDecimal totalValue;
    private Transaction.TransactionType transactionType;
    private Transaction.TransactionCategory transactionCategory;

    TransactionSnapshot(final int id, final LocalDateTime issuedOnDateTime, final BigDecimal totalValue, final Transaction.TransactionType transactionType, final Transaction.TransactionCategory transactionCategory) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategory = transactionCategory;
    }

    int getId() {
        return id;
    }

    LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    BigDecimal getTotalValue() {
        return totalValue;
    }

    Transaction.TransactionType getTransactionType() {
        return transactionType;
    }

    Transaction.TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }
}
