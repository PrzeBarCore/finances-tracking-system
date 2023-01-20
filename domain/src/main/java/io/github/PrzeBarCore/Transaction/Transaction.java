package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;


import java.time.LocalDateTime;
class Transaction {

    static Transaction restore(TransactionSnapshot transactionSnapshot){
       return new Transaction(transactionSnapshot.getId(),
        transactionSnapshot.getIssuedOnDateTime(),
        transactionSnapshot.getTotalValue(),
        transactionSnapshot.getTransactionType(),
        TransactionCategory.restore(transactionSnapshot.getTransactionCategory()),
               transactionSnapshot.getAccountId());
    }
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private TransactionCategory transactionCategory;
    private Integer accountId;

    private Transaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType, TransactionCategory transactionCategory, Integer accountId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategory = transactionCategory;
        this.accountId = accountId;
    }

    TransactionSnapshot getSnapshot(){
        return new TransactionSnapshot(this.id, this.issuedOnDateTime, this.totalValue, this.transactionType, this.transactionCategory.getSnapshot(), this.accountId);
    }

}

