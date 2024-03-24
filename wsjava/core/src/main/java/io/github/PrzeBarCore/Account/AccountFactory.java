package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;

import java.math.BigDecimal;

public class AccountFactory {
    static AccountDto createDto(Account account){
        return createDto(account.getSnapshot());
    }
    static Account createEntity(AccountDto accountDto){
        return Account.restore(createSnapshot(accountDto));
    }
    private static AccountSnapshot createSnapshot(AccountDto accountDto){
        return new AccountSnapshot(accountDto.getId(),accountDto.getName(), accountDto.getBalance(), SimpleCurrency.valueOf(accountDto.getCurrency()));
    }
    private static AccountDto createDto(AccountSnapshot accountSnapshot){
        return new AccountDto(accountSnapshot.getId(), accountSnapshot.getName(), accountSnapshot.getBalance(), accountSnapshot.getCurrency().name());
    }

}
