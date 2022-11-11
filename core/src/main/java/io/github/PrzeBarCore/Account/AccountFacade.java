package io.github.PrzeBarCore.Account;

class AccountFacade {
    private final AccountRepository accountRepository;

    AccountFacade(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountSnapshot save(AccountSnapshot accountSnapshotToSave){
        return accountRepository.save(Account.restore(accountSnapshotToSave)).getSnapshot();
    }
}
