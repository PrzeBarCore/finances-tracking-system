package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Transaction.SimpleTransactionDto;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.List;

public class AccountDto extends SimpleAccountDto{
    private MonetaryAmount balance;
    private String currency;
    List<SimpleTransactionDto> transactionList = null;

    AccountDto(){};

    public AccountDto(Integer id, NameString name, MonetaryAmount balance, String currency) {
        super(id,name);
        this.balance = balance;
        this.currency = currency;
    }

    AccountDto(Integer id, NameString name, MonetaryAmount balance, String currency, List<SimpleTransactionDto> simpleTransactionDto){
        this(id, name,balance,currency);
        this.transactionList = simpleTransactionDto;
    }

    public int getId() {
        return super.getId();
    }

    NameString getName() {
        return super.getName();
    }

    MonetaryAmount getBalance() {
        return balance;
    }

    String getCurrency() {
        return currency;
    }

    void setId(Integer id) {
        super.setId(id);
    }

    void setName(NameString name) {
        super.setName(name);
    }

    void setBalance(MonetaryAmount balance) {
        this.balance = balance;
    }

    void setCurrency(String currency) {
        this.currency = currency;
    }

    List<SimpleTransactionDto> getTransactionList() {
        return transactionList;
    }

    void setTransactionList(List<SimpleTransactionDto> transactionList) {
        this.transactionList = transactionList;
    }
}
