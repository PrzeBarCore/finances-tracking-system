package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;

import java.util.List;
import java.util.stream.Collectors;


class Account {
    static Account restore(AccountSnapshot accountSnapshot){
        return new Account(accountSnapshot.getId(),
                accountSnapshot.getName(),
                accountSnapshot.getBalance(),
                accountSnapshot.getCurrency());
    }
    private int id;
    private NameString nameString;
    private MonetaryAmount balance;
    private SimpleCurrency currency;

    private Account(int id, NameString nameString, MonetaryAmount balance,SimpleCurrency currency) {
        this.id = id;
        this.nameString = nameString;
        this.balance = balance;
        this.currency=currency;
    }

    AccountSnapshot getSnapshot(){
        return new AccountSnapshot(this.id, this.nameString, this.balance,this.currency);
    }

}
