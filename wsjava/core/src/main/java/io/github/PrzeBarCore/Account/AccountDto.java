package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Transaction.TransactionDto;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;

import java.util.List;

public class AccountDto {
    private Integer id;
    private String name;
    private Double balance;
    private String currency;
    List<TransactionDto> transactionList = null;

    AccountDto(){};

    public AccountDto(Integer id, String name, Double balance, String currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public AccountDto(Integer id, String name, Double balance, String currency, List<TransactionDto> transactionDto){
        this(id, name,balance,currency);
        this.transactionList = transactionDto;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<TransactionDto> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionDto> transactionList) {
        this.transactionList = transactionList;
    }
}
