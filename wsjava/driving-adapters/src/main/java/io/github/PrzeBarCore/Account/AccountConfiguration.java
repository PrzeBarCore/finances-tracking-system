package io.github.PrzeBarCore.Account;

import io.github.PrzeBarCore.Transaction.TransactionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class AccountConfiguration {
    AccountConfiguration() {
    }

    @Bean
    AccountFacade accountFacade(final AccountRepository accountRepository, @Lazy final TransactionFacade transactionFacade){
        return new AccountFacade(accountRepository, transactionFacade);
    }
}
