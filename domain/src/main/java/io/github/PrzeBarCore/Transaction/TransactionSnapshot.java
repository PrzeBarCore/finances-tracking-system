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
    private TransactionCategorySnapshot transactionCategory;
    private Description description;
    private int targetAccountId;
    private int sourceAccountId;
    private String borrowDescription;
    private LocalDateTime repaymentDate;
    private int receiptId;

    protected TransactionSnapshot(){}
    TransactionSnapshot(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType,
                                TransactionCategorySnapshot transactionCategory, Description description, Integer targetAccountId,
                        Integer sourceAccountId, String borrowDescription, LocalDateTime repaymentDate, Integer receiptId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategory = transactionCategory;
        this.description=description;
        this.targetAccountId = targetAccountId;
        this.sourceAccountId = sourceAccountId;
        this.borrowDescription=borrowDescription;
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

    TransactionCategorySnapshot getTransactionCategory() {
        return transactionCategory;
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

    String getBorrowDescription() {
        return borrowDescription;
    }

    LocalDateTime getRepaymentDate() {
        return repaymentDate;
    }

    int getReceiptId() {
        return receiptId;
    }
}
