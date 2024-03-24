package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.SimpleAccountDto;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

 public class InnerTypeSimpleTransactionDto extends SimpleTransactionDto {
    private Description description;
    private SimpleAccountDto targetAccount;
    private SimpleAccountDto sourceAccount;

    InnerTypeSimpleTransactionDto(){}
     public InnerTypeSimpleTransactionDto(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, Description description, SimpleAccountDto targetAccount, SimpleAccountDto sourceAccount) {
        super(id, issuedOnDateTime, totalValue, TransactionType.INNER_TRANSFER);
        this.description = description;
        this.targetAccount = targetAccount;
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

     SimpleAccountDto getTargetAccount() {
        return this.targetAccount;
    }

     void setTargetAccount(AccountDto sourceAccount) {
        this.targetAccount = targetAccount;
    }

     SimpleAccountDto getSourceAccount() {
        return this.sourceAccount;
    }

     void setSourceAccount(AccountDto sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
}
