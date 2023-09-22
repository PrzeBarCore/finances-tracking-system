package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Product.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReceiptConfiguration {

    @Bean
    ReceiptFacade receiptFacade(final ReceiptRepository repository, final ProductFacade productFacade){
        return new ReceiptFacade(repository, productFacade);
    }

}
