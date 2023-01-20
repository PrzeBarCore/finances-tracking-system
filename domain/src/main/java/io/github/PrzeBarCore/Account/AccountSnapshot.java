package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;

import java.util.List;

class AccountSnapshot {
    private int id;
    private NameString name;
    private MonetaryAmount balance;
    private SimpleCurrency currency;
    private List<Integer> doneTransactionIds;

    protected AccountSnapshot(){}

    AccountSnapshot(int id, NameString name, MonetaryAmount balance, SimpleCurrency currency, List<Integer> doneTransactionIds) {
        this.id=id;
        this.name = name;
        this.balance = balance;
        this.currency=currency;
        this.doneTransactionIds = doneTransactionIds;
    }

    int getId() {
        return id;
    }
    NameString getName() {
        return name;
    }
    MonetaryAmount getBalance() {
        return balance;
    }
    SimpleCurrency getCurrency() {
        return currency;
    }
    List<Integer> getDoneTransactionIds() {
        return doneTransactionIds;
    }

}
