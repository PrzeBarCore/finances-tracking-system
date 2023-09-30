package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

class TransactionSnapshot {
    private int id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Integer transactionCategoryId;
    private Description description;
    private int targetAccountId;
    private int sourceAccountId;
    private LocalDateTime repaymentDate;
    private int receiptId;

    protected TransactionSnapshot(){}
    TransactionSnapshot(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType,
                        Integer transactionCategoryId, Description description, Integer targetAccountId,
                        Integer sourceAccountId, LocalDateTime repaymentDate, Integer receiptId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategoryId = transactionCategoryId;
        this.description=description;
        this.targetAccountId = targetAccountId;
        this.sourceAccountId = sourceAccountId;
        this.repaymentDate=repaymentDate;
        this.receiptId=receiptId;
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

    Integer getTransactionCategoryId() {
        return transactionCategoryId;
    }

    Description getDescription() {
        return description;
    }

    int getTargetAccountId() {
        return targetAccountId;
    }

    int getSourceAccountId() {
        return sourceAccountId;
    }

    LocalDateTime getRepaymentDate() {
        return repaymentDate;
    }

    int getReceiptId() {
        return receiptId;
    }
}
