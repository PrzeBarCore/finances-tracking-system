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

    public Optional<AccountDto> add(AccountDto accountDto) {
        if(!accountRepository.existsById(accountDto.getId()))
            return save(accountDto);
        return Optional.empty();
    }

    public Optional<AccountDto> updateAccount(AccountDto accountDto) {
        if(accountRepository.existsById(accountDto.getId()))
            return save(accountDto);
        return Optional.empty();
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

    private Optional<AccountDto>save(AccountDto accountDto){
        return Optional.ofNullable(DtoFromSnapshot(accountRepository.save(Account.restore(snapshotFromDto(accountDto))).getSnapshot()));
    }

    //TODO na razie bez tranzakcji
    private AccountSnapshot snapshotFromDto(AccountDto accountDto){
        return new AccountSnapshot(accountDto.getId(),accountDto.getName(), accountDto.getBalance(), accountDto.getCurrency());
    }
    //bez transakcjii
    private AccountDto DtoFromSnapshot(AccountSnapshot accountSnapshot){
        return AccountDto.builder().withId(accountSnapshot.getId()).withAccountName(accountSnapshot.getName()).withBalance(accountSnapshot.getBalance()).withCurrency(accountSnapshot.getCurrency()).build();
    }
}
