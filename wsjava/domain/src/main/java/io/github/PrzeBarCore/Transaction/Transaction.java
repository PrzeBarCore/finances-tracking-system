package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

class Transaction {

    static Transaction restore(TransactionSnapshot snapshot){
        return new Transaction(
                snapshot.getId(),
                snapshot.getIssuedOnDateTime(),
                snapshot.getTotalValue(),
                snapshot.getTransactionType(),
                snapshot.getDescription(),
                snapshot.getRepaymentDate(),
                snapshot.getTransactionCategoryId(),
                snapshot.getTargetAccountId(),
                snapshot.getSourceAccountId(),
                snapshot.getReceipt() != null ? Receipt.restore(snapshot.getReceipt()) : null
        );
    }
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Integer transactionCategoryId;
    private Description description;
    private LocalDateTime repaymentDate;
    private Integer targetAccountId;
    private Integer sourceAccountId;
    private Receipt receipt;

    private Transaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType,
                        Description description, LocalDateTime repaymentDate, Integer transactionCategoryId, Integer targetAccountId,
                        Integer sourceAccountId, Receipt receipt) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.description = description;
        this.repaymentDate = repaymentDate;
        this.transactionCategoryId = transactionCategoryId;
        this.targetAccountId = targetAccountId;
        this.sourceAccountId = sourceAccountId;
        this.receipt = receipt;
    }

    TransactionSnapshot getSnapshot() {
        return new TransactionSnapshot(this.id,
                this.issuedOnDateTime,
                this.totalValue,
                this.transactionType,
                this.description,
                this.repaymentDate,
                this.transactionCategoryId,
                this.targetAccountId,
                this.sourceAccountId,
                this.receipt != null ? this.receipt.getSnapshot() : null);
    }

}





