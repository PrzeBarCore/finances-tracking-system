package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionConfiguration {

    @Bean
    TransactionFactory transactionFactory(AccountFacade accountFacade, ReceiptFacade receiptFacade, CategoryFacade categoryFacade){
        return new TransactionFactory(accountFacade, receiptFacade, categoryFacade);
    }
    @Bean
    TransactionFacade transactionFacade(final TransactionRepository repository, final TransactionFactory transactionRepository){
        return new TransactionFacade(repository, transactionRepository);
    }
}
