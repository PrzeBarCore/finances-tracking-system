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
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private Double totalValue;
    private String transactionType;
    private String description;
    private LocalDateTime repaymentDate;
    private Optional<CategoryDto> transactionCategory;
    private Optional<AccountDto> targetAccount;
    private Optional<AccountDto> sourceAccount;
    private Optional<ReceiptDto> receipt;

     TransactionDto(){}

    public TransactionDto(Integer id, LocalDateTime issuedOnDateTime, Double totalValue, String transactionType, String description, LocalDateTime repaymentDate, Optional<CategoryDto> transactionCategory, Optional<AccountDto> targetAccount, Optional<AccountDto> sourceAccount, Optional<ReceiptDto> receipt) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.description = description;
        this.repaymentDate = repaymentDate;
        this.transactionCategory = transactionCategory;
        this.targetAccount = targetAccount;
        this.sourceAccount = sourceAccount;
        this.receipt = receipt;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDescription() { return description; }

    public LocalDateTime getRepaymentDate() { return repaymentDate; }
    public Optional<CategoryDto> getTransactionCategory() { return transactionCategory; }

    public Optional<AccountDto> getTargetAccount() {
        return targetAccount;
    }

    public Optional<AccountDto> getSourceAccount() {
        return sourceAccount;
    }

    public Optional<ReceiptDto> getReceipt() {
        return receipt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIssuedOnDateTime(LocalDateTime issuedOnDateTime) {
        this.issuedOnDateTime = issuedOnDateTime;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRepaymentDate(LocalDateTime repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public void setTransactionCategory(Optional<CategoryDto> transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public void setTargetAccount(Optional<AccountDto> targetAccount) {
        this.targetAccount = targetAccount;
    }

    public void setSourceAccount(Optional<AccountDto> sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setReceipt(Optional<ReceiptDto> receipt) {
        this.receipt = receipt;
    }
}
