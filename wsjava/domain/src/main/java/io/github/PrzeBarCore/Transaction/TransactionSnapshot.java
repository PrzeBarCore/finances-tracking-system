package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

class TransactionSnapshot {
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Description description;
    private LocalDateTime repaymentDate;
    private Integer transactionCategoryId;
    private Integer targetAccountId;
    private Integer sourceAccountId;
    private Integer receiptId;

    protected TransactionSnapshot(){}
    TransactionSnapshot(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType,
                        Description description, LocalDateTime repaymentDate, Integer transactionCategoryId, Integer targetAccountId,
                        Integer sourceAccountId, Integer receiptId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.description=description;
        this.repaymentDate=repaymentDate;
        this.transactionCategoryId = transactionCategoryId;
        this.targetAccountId = targetAccountId;
        this.sourceAccountId = sourceAccountId;
        this.receiptId = receiptId;
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

    TransactionType getTransactionType() {
        return transactionType;
    }

    Integer getTransactionCategoryId() {
        return transactionCategoryId;
    }

    Description getDescription() {
        return description;
    }

    Integer getTargetAccountId() {
        return targetAccountId;
    }

    Integer getSourceAccountId() {
        return sourceAccountId;
    }

    LocalDateTime getRepaymentDate() {
        return repaymentDate;
    }

    Integer getReceiptId() {return this.receiptId;}
}
