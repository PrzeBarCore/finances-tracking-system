package io.github.PrzeBarCore.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
class Transaction {
    enum TransactionType{
        INCOME, OUTCOME
    }
    static Transaction restore(TransactionSnapshot transactionSnapshot){
        var transaction=new Transaction();
        transaction.id= transactionSnapshot.getId();
        transaction.issuedOnDateTime=transactionSnapshot.getIssuedOnDateTime();
        transaction.totalValue=transactionSnapshot.getTotalValue();
        transaction.transactionType=transactionSnapshot.getTransactionType();
        transaction.transactionCategory= transactionSnapshot.getTransactionCategory();
        return transaction;
    }
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private BigDecimal totalValue;
    private TransactionType transactionType;
    private TransactionCategory transactionCategory;

    private Transaction(){}

    TransactionSnapshot getSnapshot(){
        return new TransactionSnapshot(this.id, this.issuedOnDateTime, this.totalValue, this.transactionType, this.transactionCategory);
    }
    class TransactionCategory {
        private Integer id;
        private String name;
    }
}

