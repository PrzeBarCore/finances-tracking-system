package io.github.PrzeBarCore.Transaction;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;
import java.time.LocalDateTime;

public class SimpleTransactionDto {
     private Integer id;
     private LocalDateTime issuedOnDateTime;
     private MonetaryAmount totalValue;
     private TransactionType transactionType;

    SimpleTransactionDto(){}
    SimpleTransactionDto(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
    }

    Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    void setIssuedOnDateTime(LocalDateTime issuedOnDateTime) {
        this.issuedOnDateTime = issuedOnDateTime;
    }

    MonetaryAmount getTotalValue() {
        return totalValue;
    }

    void setTotalValue(MonetaryAmount totalValue) {
        this.totalValue = totalValue;
    }

    TransactionType getTransactionType() {
        return transactionType;
    }

    void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
