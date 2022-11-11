package io.github.PrzeBarCore.Account;

import java.util.List;
import java.util.Optional;

interface AccountRepository {
    Account save(Account entity);
    Optional<Account> findById(Integer id);
    List<Account> findAll();
}
