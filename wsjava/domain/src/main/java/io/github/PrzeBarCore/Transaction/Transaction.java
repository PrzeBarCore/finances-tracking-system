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
                snapshot.getTransactionCategoryId(),
                snapshot.getDescription(),
                snapshot.getTargetAccountId(),
                snapshot.getSourceAccountId(),
                snapshot.getRepaymentDate(),
                snapshot.getReceiptId()
        );
    }
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Integer transactionCategoryId;
    private Description description;
    private Integer targetAccountId;
    private Integer sourceAccountId;
    private LocalDateTime repaymentDate;
    private Integer receiptId;

    private Transaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType,
                        Integer transactionCategoryId, Description description, Integer targetAccountId,
                        Integer sourceAccountId, LocalDateTime repaymentDate, Integer receiptId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategoryId = transactionCategoryId;
        this.description = description;
        this.targetAccountId = targetAccountId;
        this.sourceAccountId = sourceAccountId;
        this.repaymentDate = repaymentDate;
        this.receiptId = receiptId;
    }

    TransactionSnapshot getSnapshot() {
        return new TransactionSnapshot(this.id,
                this.issuedOnDateTime,
                this.totalValue,
                this.transactionType,
                this.transactionCategoryId,
                this.description,
                this.targetAccountId,
                this.sourceAccountId,
                this.repaymentDate,
                this.receiptId);
    }

}





