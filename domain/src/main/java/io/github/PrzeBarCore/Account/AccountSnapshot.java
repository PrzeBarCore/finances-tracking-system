package io.github.PrzeBarCore.Account;

import java.math.BigDecimal;
import java.util.List;

class AccountSnapshot {
    private int id;
    private String name;
    private BigDecimal balance;
    private List<TransactionSnapshot> doneTransactions;

    AccountSnapshot(int id, String name, BigDecimal balance, List<TransactionSnapshot> doneTransactions) {
        this.id=id;
        this.name = name;
        this.balance = balance;
        this.doneTransactions = doneTransactions;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    BigDecimal getBalance() {
        return balance;
    }

    List<TransactionSnapshot> getDoneTransactions() {
        return doneTransactions;
    }
}
