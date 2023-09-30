package io.github.PrzeBarCore.Transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFacade transactionFacade(TransactionRepository repository){
        return new TransactionFacade(repository);
    }
}
