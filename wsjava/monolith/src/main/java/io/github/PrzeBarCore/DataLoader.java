package io.github.PrzeBarCore;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import io.github.PrzeBarCore.Transaction.TransactionDto;
import io.github.PrzeBarCore.Transaction.TransactionFacade;
import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.SimpleCurrency;
import io.github.PrzeBarCore.ValueObjects.TransactionType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Profile("dev")
public class DataLoader implements ApplicationRunner {
    final CategoryFacade categoryFacade;
    final ReceiptFacade receiptFacade;
    final ProductFacade productFacade;
    final AccountFacade accountFacade;
    final TransactionFacade transactionFacade;

    DataLoader(final CategoryFacade categoryFacade, final ReceiptFacade receiptFacade, final ProductFacade productFacade, final AccountFacade accountFacade, final TransactionFacade transactionFacade) {
        this.categoryFacade = categoryFacade;
        this.receiptFacade=receiptFacade;
        this.productFacade = productFacade;
        this.accountFacade = accountFacade;
        this.transactionFacade= transactionFacade;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AccountDto account1 = new AccountDto(0, "PKO BP", 30000.0, SimpleCurrency.PLN.name());
        AccountDto account2 = new AccountDto(0, "PKO SA", 12000.0, SimpleCurrency.PLN.name());
        AccountDto account3 = new AccountDto(0, "Portfel", 100.0, SimpleCurrency.PLN.name());

        accountFacade.createAccount(account1);
        accountFacade.createAccount(account2);
        accountFacade.createAccount(account3);


        CategoryDto receiptCat1=new CategoryDto(0,"Zakupy spożywcze", CategoryType.RECEIPT,null,null);
        CategoryDto receiptCat2=new CategoryDto(0,"Komunikacja", CategoryType.RECEIPT,null,null);
        categoryFacade.createCategory(receiptCat1);
        categoryFacade.createCategory(receiptCat2);
        categoryFacade.createCategory(new CategoryDto(0,"Alkohol", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(1).get()));
        categoryFacade.createCategory(new CategoryDto(0,"Mąka", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(1).get()));
        categoryFacade.createCategory(new CategoryDto(0,"Bilety autobusowe", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(2).get()));
        categoryFacade.createCategory(new CategoryDto(0,"Bilety ", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(5).get()));
        categoryFacade.createCategory(new CategoryDto(0," autobusowe", CategoryType.RECEIPT,null,categoryFacade.findCategoryById(6).get()));

        CategoryDto productCat1=new CategoryDto(0,"Mąka", CategoryType.PRODUCT,null,null);
        CategoryDto productCat2=new CategoryDto(0,"Alkohol", CategoryType.PRODUCT,null,null);
        categoryFacade.createCategory(productCat1);
        categoryFacade.createCategory(productCat2);
        categoryFacade.createCategory(new CategoryDto(0," Pszenna", CategoryType.PRODUCT,null,categoryFacade.findCategoryById(8).get()));
        categoryFacade.createCategory(new CategoryDto(0," Żytnia", CategoryType.PRODUCT,null,categoryFacade.findCategoryById(8).get()));
        categoryFacade.createCategory(new CategoryDto(0," Reszta", CategoryType.PRODUCT,null,categoryFacade.findCategoryById(8).get()));
        categoryFacade.createCategory(new CategoryDto(0," Wino", CategoryType.PRODUCT,null,categoryFacade.findCategoryById(9).get()));
        categoryFacade.createCategory(new CategoryDto(0," Piwo", CategoryType.PRODUCT,null,categoryFacade.findCategoryById(9).get()));

        productFacade.createProduct((new ProductDto(0,"Mąka Tortowa 550", "Basia", 1.0, "kg", categoryFacade.findCategoryById(10).get(), 5.99, categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,"Mąka Żytnia 2000", "Basia", 1.0, "kg", categoryFacade.findCategoryById(11).get(), 5.99, categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,"Mąka Orkiszowa 610", "Basia", 1.0, "kg", categoryFacade.findCategoryById(12).get(), 5.99, categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,"Mąka Pszenna 1100", "Basia", 1.0, "kg", categoryFacade.findCategoryById(10).get(), 5.99, categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,"Mąka Żytnia 610", "Basia", 1.0, "kg", categoryFacade.findCategoryById(11).get(), 5.99, categoryFacade.findCategoryById(2).get())));
        productFacade.createProduct((new ProductDto(0,"Kozel Cerny", "Kozel", 0.5, "l", categoryFacade.findCategoryById(14).get(), 5.99, categoryFacade.findCategoryById(1).get())));

        transactionFacade.addTransaction(new TransactionDto(0,
                LocalDateTime.now(),
                2137.0,
                TransactionType.INNER_TRANSFER.name(),
                "",
                null,
                Optional.empty(),
                accountFacade.findAccount(1),
                accountFacade.findAccount(2),
                Optional.empty()));
        transactionFacade.addTransaction(new TransactionDto(0,
                LocalDateTime.now(),
                420.0,
                TransactionType.INCOME.name(),
                "",
                null,
                Optional.empty(),
                accountFacade.findAccount(1),
                Optional.empty(),
                Optional.empty()));
    }

//       this.id = id;
//        this.issuedOnDateTime = issuedOnDateTime;
//        this.totalValue = totalValue;
//        this.transactionType = transactionType;
//        this.description = description;
//        this.repaymentDate = repaymentDate;
//        this.transactionCategory = transactionCategory;
//        this.targetAccount = targetAccount;
//        this.sourceAccount = sourceAccount;
//        this.receipt = receipt;

}
