package io.github.PrzeBarCore.Account;

public class AccountFactory {
    static AccountDto createDto(Account account){
        return createDto(account.getSnapshot());
    }
    static Account createEntity(AccountDto accountDto){
        return Account.restore(createSnapshot(accountDto));
    }
    private static AccountSnapshot createSnapshot(AccountDto accountDto){
        return new AccountSnapshot(accountDto.getId(),accountDto.getName(), accountDto.getBalance(), accountDto.getCurrency());
    }
    private static AccountDto createDto(AccountSnapshot accountSnapshot){
        return AccountDto.builder().withId(accountSnapshot.getId()).withAccountName(accountSnapshot.getName()).withBalance(accountSnapshot.getBalance()).withCurrency(accountSnapshot.getCurrency()).build();
    }

}
