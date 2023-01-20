package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

class TransactionSnapshot {
    private int id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private TransactionCategorySnapshot transactionCategory;
    private Integer accountId;

    protected TransactionSnapshot(){}

    TransactionSnapshot(final int id, final LocalDateTime issuedOnDateTime, final MonetaryAmount totalValue, final TransactionType transactionType, final TransactionCategorySnapshot transactionCategory, final Integer accountId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategory = transactionCategory;
        this.accountId =accountId;
    }

    int getId() {
        return id;
    }

    LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    MonetaryAmount getTotalValue() {
        return totalValue;
    }

    TransactionType getTransactionType() {
        return transactionType;
    }

    TransactionCategorySnapshot getTransactionCategory() {
        return transactionCategory;
    }
    Integer getAccountId() {
        return accountId;
    }
}
