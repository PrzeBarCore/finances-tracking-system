package io.github.PrzeBarCore.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


class Account {
    static Account restore(AccountSnapshot accountSnapshot){
        return new Account(accountSnapshot.getId(),
                accountSnapshot.getName(),
                accountSnapshot.getBalance(),
                accountSnapshot.getDoneTransactions().stream()
                        .map(Transaction::restore)
                        .collect(Collectors.toList()));
    }
    private int id;
    private String name;
    private BigDecimal balance;
    private List<Transaction> doneTransactions;

    private Account(int id, String name, BigDecimal balance, List<Transaction> doneTransactions) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.doneTransactions = doneTransactions;
    }

    AccountSnapshot getSnapshot(){
        return new AccountSnapshot(this.id, this.name, this.balance, this.doneTransactions.stream()
                .map(Transaction::getSnapshot)
                .collect(Collectors.toList()));
    }

    void transferMoneyTo(BigDecimal transactionValue, Account destinationAccount){
        if(transactionValue!= null
                && transactionValue.compareTo(BigDecimal.ZERO)==1
                && this.balance.compareTo(BigDecimal.ZERO)==1){
            var timeStamp= LocalDateTime.now();
            this.createNewTransaction(timeStamp,transactionValue,Transaction.TransactionType.OUTCOME,null);
            destinationAccount.createNewTransaction(timeStamp,transactionValue,Transaction.TransactionType.INCOME,null);
        }
        //TODO else rhrow warning
    }

     void createNewTransaction(LocalDateTime issuedOnDateTime, BigDecimal totalValue, Transaction.TransactionType transactionType, Transaction.TransactionCategory transactionCategory){
        if(transactionType.equals(Transaction.TransactionType.INCOME))
            this.balance.add(totalValue);
          else
            this.balance.subtract(totalValue);
        this.doneTransactions.add(Transaction.restore(
                new TransactionSnapshot(0, issuedOnDateTime,totalValue,transactionType,transactionCategory)));
    }


}
