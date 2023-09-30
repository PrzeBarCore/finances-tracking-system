package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

public class TransactionDto {
    private int id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Description description;
    private LocalDateTime repaymentDate;
    private Integer transactionCategoryId;
    private int targetAccountId;
    private int sourceAccountId;
    private int receiptId;

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

    public Integer getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public Description getDescription() {
        return description;
    }

    public int getTargetAccountId() {
        return targetAccountId;
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public LocalDateTime getRepaymentDate() {
        return repaymentDate;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public static class Builder{
        private int id;
        private LocalDateTime issuedOnDateTime;
        private MonetaryAmount totalValue;
        private TransactionType transactionType;
        private Integer transactionCategoryId;
        private Description description;
        private int targetAccountId;
        private int sourceAccountId;
        private LocalDateTime repaymentDate;
        private int receiptId;

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

        public Builder withTransactionCategoryId(Integer transactionCategoryId) {
            this.transactionCategoryId = transactionCategoryId;
            return this;
        }

        public Builder withDescription(Description description) {
            this.description = description;
            return this;
        }

        public Builder withTargetAccountId(Integer targetAccountId) {
            this.targetAccountId = targetAccountId;
            return this;
        }

        public Builder withSourceAccountId(Integer sourceAccountId) {
            this.sourceAccountId = sourceAccountId;
            return this;
        }

        public Builder withReceiptId(Integer receiptId) {
            this.receiptId = receiptId;
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
            dto.transactionCategoryId=this.transactionCategoryId;
            dto.description=this.description;
            dto.sourceAccountId=this.sourceAccountId;
            dto.targetAccountId=this.targetAccountId;
            dto.receiptId=this.receiptId;
            dto.repaymentDate=this.repaymentDate;
            return dto;
        }
    }
}
