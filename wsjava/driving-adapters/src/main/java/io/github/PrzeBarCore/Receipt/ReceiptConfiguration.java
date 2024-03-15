package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Transaction.TransactionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class ReceiptConfiguration {
    @Bean
    ReceiptFactory receiptFactory(final ProductFacade productFacade, final CategoryFacade categoryFacade){
        return new ReceiptFactory(productFacade,categoryFacade);
    }
    @Bean
    ReceiptFacade receiptFacade(final ReceiptRepository repository, final ReceiptFactory receiptFactory, final ProductFacade productFacade, final CategoryFacade categoryFacade, @Lazy final TransactionFacade transactionFacade){
        return new ReceiptFacade(repository, receiptFactory, productFacade, categoryFacade, transactionFacade);
    }

}
