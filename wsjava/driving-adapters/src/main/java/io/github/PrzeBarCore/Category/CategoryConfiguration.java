package io.github.PrzeBarCore.Category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
class CategoryConfiguration {
    @Bean
    CategoryFacade categoryFacade(CategoryRepository repository){
        return new CategoryFacade(repository);
    }


        
}
