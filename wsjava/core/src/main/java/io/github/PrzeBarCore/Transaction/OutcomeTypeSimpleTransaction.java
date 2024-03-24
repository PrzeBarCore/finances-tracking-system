package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.SimpleAccountDto;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

 public class OutcomeTypeSimpleTransaction extends SimpleTransactionDto {
    private Description description;
    private CategoryDto transactionCategory;
    private SimpleAccountDto sourceAccount;

     OutcomeTypeSimpleTransaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, Description description, CategoryDto transactionCategory, SimpleAccountDto sourceAccount) {
        super(id, issuedOnDateTime, totalValue, TransactionType.INCOME);
        this.description = description;
        this.transactionCategory = transactionCategory;
        this.sourceAccount = sourceAccount;
    }


     Integer getId() {
        return super.getId();
    }

     void setId(Integer id) {super.setId(id);
    }

     LocalDateTime getIssuedOnDateTime() { return super.getIssuedOnDateTime(); }

     void setIssuedOnDateTime(LocalDateTime issuedOnDateTime) {
        super.setIssuedOnDateTime(issuedOnDateTime);
    }

     MonetaryAmount getTotalValue() {
        return super.getTotalValue();
    }

     void setTotalValue(MonetaryAmount totalValue) {
        super.setTotalValue(totalValue);
    }

     TransactionType getTransactionType() {
        return super.getTransactionType();
    }

     void setTransactionType(TransactionType transactionType) {
        super.setTransactionType(transactionType);
    }

     Description getDescription() {
        return description;
    }

     void setDescription(Description description) {
        this.description = description;
    }

     void setTransactionCategory(CategoryDto transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

     CategoryDto getTransactionCategory() {
        return transactionCategory;
    }

     void setSourceAccount(SimpleAccountDto sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

     SimpleAccountDto getSourceAccount() {
        return sourceAccount;
    }
}
