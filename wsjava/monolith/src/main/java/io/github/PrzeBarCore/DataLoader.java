package io.github.PrzeBarCore;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Transaction.IncomeTypeTransaction;
import io.github.PrzeBarCore.Transaction.InnerTypeTransaction;
import io.github.PrzeBarCore.Transaction.TransactionFacade;
import io.github.PrzeBarCore.ValueObjects.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("dev")
public class DataLoader implements ApplicationRunner {
    final CategoryFacade categoryFacade;
    final ProductFacade productFacade;
    final AccountFacade accountFacade;
    final TransactionFacade transactionFacade;

    DataLoader(final CategoryFacade categoryFacade, final ProductFacade productFacade, final AccountFacade accountFacade, final TransactionFacade transactionFacade) {
        this.categoryFacade = categoryFacade;
        this.productFacade = productFacade;
        this.accountFacade = accountFacade;
        this.transactionFacade= transactionFacade;


    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AccountDto account1 = new AccountDto(0, NameString.of("PKO BP"), MonetaryAmount.of(30000.0), SimpleCurrency.PLN.name());
        AccountDto account2 = new AccountDto(0, NameString.of("PKO SA"), MonetaryAmount.of(12000.0), SimpleCurrency.PLN.name());
        AccountDto account3 = new AccountDto(0, NameString.of("Portfel"), MonetaryAmount.of(100.0), SimpleCurrency.PLN.name());

        accountFacade.createAccount(account1);
        accountFacade.createAccount(account2);
        accountFacade.createAccount(account3);


        CategoryDto receiptCat1=new CategoryDto(0,NameString.of("Zakupy spożywcze"), CategoryType.RECEIPT,null,null);
        CategoryDto receiptCat2=new CategoryDto(0,NameString.of("Komunikacja"), CategoryType.RECEIPT,null,null);
        categoryFacade.createCategory(receiptCat1);
        categoryFacade.createCategory(receiptCat2);
        categoryFacade.createCategory(new CategoryDto(0,NameString.of("Alkohol"), CategoryType.RECEIPT,null,categoryFacade.findCategoryById(1).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of("Mąka"), CategoryType.RECEIPT,null,categoryFacade.findCategoryById(1).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of("Bilety autobusowe"), CategoryType.RECEIPT,null,categoryFacade.findCategoryById(2).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of("Bilety "), CategoryType.RECEIPT,null,categoryFacade.findCategoryById(5).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of(" autobusowe"), CategoryType.RECEIPT,null,categoryFacade.findCategoryById(6).get()));

        CategoryDto productCat1=new CategoryDto(0,NameString.of("Mąka"), CategoryType.PRODUCT,null,null);
        CategoryDto productCat2=new CategoryDto(0,NameString.of("Alkohol"), CategoryType.PRODUCT,null,null);
        categoryFacade.createCategory(productCat1);
        categoryFacade.createCategory(productCat2);
        categoryFacade.createCategory(new CategoryDto(0,NameString.of(" Pszenna"), CategoryType.PRODUCT,null,categoryFacade.findCategoryById(8).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of(" Żytnia"), CategoryType.PRODUCT,null,categoryFacade.findCategoryById(8).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of(" Reszta"), CategoryType.PRODUCT,null,categoryFacade.findCategoryById(8).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of(" Wino"), CategoryType.PRODUCT,null,categoryFacade.findCategoryById(9).get()));
        categoryFacade.createCategory(new CategoryDto(0,NameString.of(" Piwo"), CategoryType.PRODUCT,null,categoryFacade.findCategoryById(9).get()));

        CategoryDto transactionCat1=new CategoryDto(0,NameString.of("Praca"), CategoryType.TRANSACTION,null,null);
        CategoryDto transactionCat2=new CategoryDto(0,NameString.of("Rycerstwo"), CategoryType.TRANSACTION,null,null);
        categoryFacade.createCategory(transactionCat1);
        categoryFacade.createCategory(transactionCat2);

        productFacade.createProduct((new ProductDto(0,NameString.of("Mąka Tortowa 550"), Company.of("Basia"), 1.0, Unit.kg, categoryFacade.findCategoryById(10).get(), MonetaryAmount.of(5.99), categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,NameString.of("Mąka Żytnia 2000"), Company.of("Basia"), 1.0, Unit.kg, categoryFacade.findCategoryById(11).get(), MonetaryAmount.of(5.99), categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,NameString.of("Mąka Orkiszowa 610"), Company.of("Basia"), 1.0, Unit.kg, categoryFacade.findCategoryById(12).get(), MonetaryAmount.of(5.99), categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,NameString.of("Mąka Pszenna 1100"), Company.of("Basia"), 1.0, Unit.kg, categoryFacade.findCategoryById(10).get(), MonetaryAmount.of(5.99), categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,NameString.of("Mąka Żytnia 610"), Company.of("Basia"), 1.0, Unit.kg, categoryFacade.findCategoryById(11).get(), MonetaryAmount.of(5.99), categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,NameString.of("Kozel Cerny"), Company.of("Kozel"), 0.5, Unit.l, categoryFacade.findCategoryById(14).get(), MonetaryAmount.of(5.99), categoryFacade.findCategoryById(1).get())));

        transactionFacade.addTransaction(new InnerTypeTransaction(0,
                LocalDateTime.now(),
                MonetaryAmount.of(2137.0),
                Description.of(""),
                accountFacade.findAccount(1).get(),
                accountFacade.findAccount(2).get()));
        transactionFacade.addTransaction(new IncomeTypeTransaction(0,
                LocalDateTime.now(),
                MonetaryAmount.of(420.0),
                Description.of(""),
                categoryFacade.findCategoryById(15).get(),
                accountFacade.findAccount(1).get()));

    }
}
