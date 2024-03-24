package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.SimpleAccountDto;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;
import java.util.Optional;

 public class LoanTypeSimpleTransaction extends SimpleTransactionDto {
    private Description description;
    private LocalDateTime repaymentDate;
    private Optional<SimpleAccountDto> targetAccount;
    private Optional<SimpleAccountDto> sourceAccount;

     LoanTypeSimpleTransaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, Description description, LocalDateTime repaymentDate, SimpleAccountDto targetAccount, SimpleAccountDto sourceAccount) {
        super(id, issuedOnDateTime, totalValue, totalValue.compareTo(MonetaryAmount.zero()) == 1 ? TransactionType.TAKEN_LOAN : TransactionType.GIVEN_LOAN);
        this.description = description;
        this.repaymentDate = repaymentDate;
        if(this.getTransactionType().equals(TransactionType.GIVEN_LOAN.toString())){
            this.sourceAccount = Optional.of(sourceAccount);
            this.targetAccount = Optional.empty();
        } else {
            this.sourceAccount = Optional.empty();
            this.targetAccount = Optional.of(targetAccount);
        }
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

     LocalDateTime getRepaymentDate() {
        return repaymentDate;
    }

     void setRepaymentDate(LocalDateTime repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

     Optional<SimpleAccountDto> getTargetAccount() {
        return targetAccount;
    }

     void setTargetAccount(Optional<SimpleAccountDto> targetAccount) {
        this.targetAccount = targetAccount;
    }

     Optional<SimpleAccountDto> getSourceAccount() {
        return sourceAccount;
    }

     void setSourceAccount(Optional<SimpleAccountDto> sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
}
