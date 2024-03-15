package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class TransactionConfiguration {
    @Bean
    TransactionFacade transactionFacade(final TransactionRepository repository,@Lazy final AccountFacade accountFacade,final  ReceiptFacade receiptFacade,final  CategoryFacade categoryFacade){
        return new TransactionFacade(repository,accountFacade,receiptFacade,categoryFacade);
    }
}
