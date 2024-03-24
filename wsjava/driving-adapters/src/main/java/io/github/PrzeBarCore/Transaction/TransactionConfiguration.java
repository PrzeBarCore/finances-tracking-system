package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class TransactionConfiguration {
    @Bean
    TransactionFacade transactionFacade(final TransactionRepository transactionRepository,  final ReceiptRepository receiptRepository, @Lazy final AccountFacade accountFacade, final CategoryFacade categoryFacade, final ProductFacade productFacade){
        return new TransactionFacade(transactionRepository, receiptRepository,accountFacade,categoryFacade,productFacade);
    }
}
