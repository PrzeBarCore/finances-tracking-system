package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Transaction.TransactionFacade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountFacade {
    private final AccountRepository repository;
    private final TransactionFacade transactionFacade;

    AccountFacade(final AccountRepository repository, final TransactionFacade transactionFacade) {
        this.repository = repository;
        this.transactionFacade = transactionFacade;
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

    public Optional<AccountDto> findAccountWithTransactions(int id) {
        Optional<AccountDto> foundAccount = repository.findById(id).map(AccountFactory::createDto);
        foundAccount.ifPresent(accountDto -> accountDto.setTransactionList(
                transactionFacade.findTransactionsWithAccountId(accountDto.getId()).stream()
                        .map(transaction -> {if(transaction.getSourceAccount().isPresent() && transaction.getSourceAccount().get().getId() == id)
                            transaction.setTotalValue(-transaction.getTotalValue());
                            return transaction;
                        }).collect(Collectors.toList())));
        return foundAccount;
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
