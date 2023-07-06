package io.github.PrzeBarCore.Receipt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReceiptConfiguration {

    @Bean
    ReceiptFacade receiptFacade(final ReceiptRepository repository){
        return new ReceiptFacade(repository);
    }

}
