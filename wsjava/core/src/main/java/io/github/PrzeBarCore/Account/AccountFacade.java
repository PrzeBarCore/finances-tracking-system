package io.github.PrzeBarCore.Account;

import java.util.List;
import java.util.Optional;

public class AccountFacade {
    private final AccountRepository repository;

    AccountFacade(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountDto createAccount(AccountDto accountDto) {
        if(!repository.existsById(accountDto.getId()))
            return saveAccount(accountDto);
        else
            return accountDto;
    }

    public Optional<AccountDto> updateAccount(AccountDto accountDto) {
        if(repository.existsById(accountDto.getId()))
            return Optional.of(saveAccount(accountDto));
        return Optional.empty();
    }

    public List<AccountDto> findAllAccounts(){
        return repository.findAll().stream().map(AccountFactory::createDto).toList();
    }

    public Optional<AccountDto> findAccount(int id) {
        return repository.findById(id).map(AccountFactory::createDto);
    }

    public boolean removeAccount(int id) {
        Optional<Account> result= repository.findById(id);
        if(result.isPresent()){
            repository.delete(result.get());
            return true;
        }
        return false;
    }

    private AccountDto saveAccount(AccountDto accountDto){
        return AccountFactory.createDto(repository.save(AccountFactory.createEntity(accountDto)));
    }

}
