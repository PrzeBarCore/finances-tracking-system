package io.github.PrzeBarCore.Account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AccountConfiguration {
    @Bean
    AccountFacade accountFacade(final AccountRepository accountRepository){
        return new AccountFacade(accountRepository);
    }
}
