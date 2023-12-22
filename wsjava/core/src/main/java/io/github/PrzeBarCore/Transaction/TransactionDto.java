package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Receipt.ReceiptDto;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionDto {
    private int id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Description description;
    private LocalDateTime repaymentDate;
    private Optional<CategoryDto> transactionCategory;
    private Optional<AccountDto> targetAccount;
    private Optional<AccountDto> sourceAccount;
    private Optional<ReceiptDto> receipt;

    private TransactionDto(){}

    public static Builder builder(){
        return new Builder();
    }


    public int getId() {
        return id;
    }

    public LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    public MonetaryAmount getTotalValue() {
        return totalValue;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Optional<CategoryDto> getTransactionCategory() {
        return transactionCategory;
    }

    public Description getDescription() {
        return description;
    }

    public Optional<AccountDto> getTargetAccount() {
        return targetAccount;
    }

    public Optional<AccountDto> getSourceAccount() {
        return sourceAccount;
    }

    public LocalDateTime getRepaymentDate() {
        return repaymentDate;
    }

    public Optional<ReceiptDto> getReceipt() {
        return receipt;
    }

    public static class Builder{
        private int id;
        private LocalDateTime issuedOnDateTime;
        private MonetaryAmount totalValue;
        private TransactionType transactionType;
        private Description description;
        private LocalDateTime repaymentDate;
        private Optional<CategoryDto> transactionCategory;
        private Optional<AccountDto> targetAccount;
        private Optional<AccountDto> sourceAccount;
        private Optional<ReceiptDto> receipt;

        private Builder(){}

        public Builder withId(int id){
            this.id=id;
            return this;
        }

        public Builder withIssuedOnDateTime(LocalDateTime issuedOnDateTime) {
            this.issuedOnDateTime = issuedOnDateTime;
            return this;
        }

        public Builder withTotalValue(MonetaryAmount totalValue) {
            this.totalValue = totalValue;
            return this;
        }

        public Builder withTransactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public Builder withTransactionCategory(Optional<CategoryDto> transactionCategory) {
            this.transactionCategory = transactionCategory;
            return this;
        }

        public Builder withDescription(Description description) {
            this.description = description;
            return this;
        }

        public Builder withTargetAccount(Optional<AccountDto> targetAccount) {
            this.targetAccount = targetAccount;
            return this;
        }

        public Builder withSourceAccount(Optional<AccountDto> sourceAccountId) {
            this.sourceAccount = sourceAccount;
            return this;
        }

        public Builder withReceipt(Optional<ReceiptDto> receipt) {
            this.receipt = receipt;
            return this;
        }

        public Builder withRepaymentDate(LocalDateTime repaymentDate) {
            this.repaymentDate = repaymentDate;
            return this;
        }

        public TransactionDto build(){
            var dto=new TransactionDto();
            dto.id=this.id;
            dto.issuedOnDateTime=this.issuedOnDateTime;
            dto.totalValue=this.totalValue;
            dto.transactionType=this.transactionType;
            dto.transactionCategory =this.transactionCategory;
            dto.description=this.description;
            dto.sourceAccount =this.sourceAccount;
            dto.targetAccount =this.targetAccount;
            dto.receipt =this.receipt;
            dto.repaymentDate=this.repaymentDate;
            return dto;
        }
    }
}
