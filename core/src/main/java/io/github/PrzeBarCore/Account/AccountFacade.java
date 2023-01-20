package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Account.Dto.AccountDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class AccountFacade {
    private final AccountRepository accountRepository;

    AccountFacade(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void add(AccountDto accountDto) {
        if(!accountRepository.existsById(accountDto.getId()))
         accountRepository.save(Account.restore(snapshotFromDto(accountDto)));
    }

    public List<AccountDto> findAll(){
        return accountRepository.findAll().stream().map(account -> DtoFromSnapshot(account.getSnapshot())).collect(Collectors.toList());
    }

    public Optional<AccountDto> find(int id) {
        return accountRepository.findById(id).flatMap(account -> Optional.of(DtoFromSnapshot(account.getSnapshot())));
    }

    public boolean remove(int id) {
        Optional<Account> result=accountRepository.findById(id);
        if(result.isPresent()){
            accountRepository.delete(result.get());
            return true;
        }
        return false;
    }


        //TODO na razie bez tranzakcji
    private AccountSnapshot snapshotFromDto(AccountDto accountDto){
        return new AccountSnapshot(accountDto.getId(),accountDto.getName(), accountDto.getBalance(), accountDto.getCurrency(), null);
    }
    //bez transakcjii i id
    private AccountDto DtoFromSnapshot(AccountSnapshot accountSnapshot){
        return AccountDto.builder().withAccountName(accountSnapshot.getName()).withBalance(accountSnapshot.getBalance()).withCurrency(accountSnapshot.getCurrency()).build();
    }


}
