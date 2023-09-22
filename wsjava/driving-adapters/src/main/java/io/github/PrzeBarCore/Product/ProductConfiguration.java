package io.github.PrzeBarCore.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import javax.transaction.Transactional;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
class ProductConfiguration {
    ProductConfiguration() {
    }

    ProductFacade productFacade(final ProductRepository productRepository){
        return new ProductFacade(productRepository);
    }


    @Bean
    public ProductFacade productFacadeInitialization(PlatformTransactionManager txManager, final ProductRepository productRepository) {
        TransactionProxyFactoryBean proxy = new TransactionProxyFactoryBean();
        // Inject transaction manager here
        proxy.setTransactionManager(txManager);
        // Define which object instance is to be proxied (your bean)
        proxy.setTarget(productFacade(productRepository));
        // Programmatically setup transaction attributes
        Properties transactionAttributes = new Properties();
        transactionAttributes.put("*", "PROPAGATION_REQUIRED");
        proxy.setTransactionAttributes(transactionAttributes);
        // Finish FactoryBean setup
        proxy.afterPropertiesSet();
        return (ProductFacade) proxy.getObject();
    }


}
