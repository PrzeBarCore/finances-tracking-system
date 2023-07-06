package io.github.PrzeBarCore.Transaction.Dto;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

public class TransactionDto {
    private int id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private TransactionCategoryDto transactionCategory;
    private Integer accountId;

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

    public TransactionCategoryDto getTransactionCategory() {
        return transactionCategory;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public static class Builder{
        private int id;
        private LocalDateTime issuedOnDateTime;
        private MonetaryAmount totalValue;
        private TransactionType transactionType;
        private TransactionCategoryDto transactionCategory;
        private Integer accountId;

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

        public Builder withTransactionCategory(TransactionCategoryDto transactionCategory) {
            this.transactionCategory = transactionCategory;
            return this;
        }

        public Builder withAccountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public TransactionDto build(){
            var dto=new TransactionDto();
            dto.id=this.id;
            dto.issuedOnDateTime=this.issuedOnDateTime;
            dto.totalValue=this.totalValue;
            dto.transactionType=this.transactionType;
            dto.transactionCategory=this.transactionCategory;
            dto.accountId=this.accountId;
            return dto;
        }
    }
}
