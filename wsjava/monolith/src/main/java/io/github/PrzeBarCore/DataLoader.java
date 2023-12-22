package io.github.PrzeBarCore;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import io.github.PrzeBarCore.ValueObjects.CategoryType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Profile("dev")
public class DataLoader implements ApplicationRunner {
    final CategoryFacade categoryFacade;
    final ReceiptFacade receiptFacade;

    DataLoader(final CategoryFacade categoryFacade, final ReceiptFacade receiptFacade) {
        this.categoryFacade = categoryFacade;
        this.receiptFacade=receiptFacade;}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ArrayList<CategoryDto> categoryDtos = new ArrayList<>();
        CategoryDto parent1=new CategoryDto(0,"Zakupy spożywcze", CategoryType.RECEIPT,null,null);
        CategoryDto parent2=new CategoryDto(0,"Komunikacja", CategoryType.RECEIPT,null,null);
        categoryFacade.createCategory(parent1);
        categoryFacade.createCategory(parent2);
        categoryFacade.createCategory(new CategoryDto(0,"Herbata", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(1).get()));
        categoryFacade.createCategory(new CategoryDto(0,"Ciastka", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(1).get()));
        categoryFacade.createCategory(new CategoryDto(0,"Bilety autobusowe", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(2).get()));
        categoryFacade.createCategory(new CategoryDto(0,"Bilety ", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(5).get()));
        categoryFacade.createCategory(new CategoryDto(0," autobusowe", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(6).get()));
    }

}
