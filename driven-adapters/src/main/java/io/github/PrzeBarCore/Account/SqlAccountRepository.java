package io.github.PrzeBarCore.Account;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface SqlAccountRepository extends Repository<AccountSnapshot,Integer> {

    public AccountSnapshot save(AccountSnapshot entity);

    public Optional<AccountSnapshot> findById(Integer id) ;

    public List<AccountSnapshot> findAll();
}

@org.springframework.stereotype.Repository
class AccountRepositoryImpl implements AccountRepository{

    private final SqlAccountRepository repository;

    AccountRepositoryImpl(SqlAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(final Account entity) {
        return Account.restore(repository.save(entity.getSnapshot()));
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return repository.findById(id).map(Account::restore);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll().stream()
                .map(Account::restore)
                .collect(Collectors.toList());
    }
}
