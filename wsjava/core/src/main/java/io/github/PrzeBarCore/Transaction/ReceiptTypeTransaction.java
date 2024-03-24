package io.github.PrzeBarCore.Transaction;
import io.github.PrzeBarCore.Account.SimpleAccountDto;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

 public class ReceiptTypeTransaction extends SimpleTransactionDto {
    private SimpleAccountDto sourceAccount;
    private ReceiptDto receipt;

    ReceiptTypeTransaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, SimpleAccountDto sourceAccount, ReceiptDto receipt) {
        super(id, issuedOnDateTime, totalValue, TransactionType.RECEIPT);
        this.sourceAccount = sourceAccount;
        this.receipt = receipt;
    }

     public Integer getId() {
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

     SimpleAccountDto getSourceAccount() {
        return this.sourceAccount;
    }

     void setSourceAccount(SimpleAccountDto sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

     ReceiptDto getReceipt() {
         return receipt;
     }

     public void setReceipt(ReceiptDto receipt) {
         this.receipt = receipt;
     }
 }
