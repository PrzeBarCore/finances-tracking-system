package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

class Transaction {
    private Integer id;
    private LocalDateTime issuedOnDateTime;
    private MonetaryAmount totalValue;
    private TransactionType transactionType;
    private Integer transactionCategoryId;
    private Description description;
    private Integer targetAccountId;
    private Integer sourceAccountId;
    private String borrowDescription;
    private LocalDateTime repaymentDate;
    private Integer receiptId;

    private Transaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue, TransactionType transactionType,
                        Integer transactionCategoryId, Description description, Integer targetAccountId,
                        Integer sourceAccountId, String borrowDescription, LocalDateTime repaymentDate, Integer receiptId) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionType = transactionType;
        this.transactionCategoryId = transactionCategoryId;
        this.description = description;
        this.targetAccountId = targetAccountId;
        this.sourceAccountId = sourceAccountId;
        this.repaymentDate = repaymentDate;
        this.receiptId = receiptId;
    }

    private Transaction(Integer id, LocalDateTime issuedOnDateTime, MonetaryAmount totalValue,
                        Integer transactionCategoryId, Description description) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.transactionCategoryId = transactionCategoryId;
        this.description = description;
    }

    TransactionSnapshot getSnapshot() {
        return new TransactionSnapshot(this.id,
                this.issuedOnDateTime,
                this.totalValue,
                this.transactionType,
                this.transactionCategoryId,
                this.description,
                this.targetAccountId,
                this.sourceAccountId,
                this.repaymentDate,
                this.receiptId);
    }

    private void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    private void setTargetAccountId(Integer targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    private void setSourceAccountId(Integer sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    private void setBorrowDescription(String borrowDescription) {
        this.borrowDescription = borrowDescription;
    }

    private void setRepaymentDate(LocalDateTime repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    private void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    static class Creator {
        static Transaction createIncomeTransaction(TransactionSnapshot transactionSnapshot) {
            Transaction transaction = commonCreationPart(transactionSnapshot);
            transaction.setTransactionType(TransactionType.INCOME);
            transaction.setTargetAccountId(transactionSnapshot.getTargetAccountId());
            return transaction;
        }

        static Transaction createOutcomeTransaction(TransactionSnapshot transactionSnapshot) {
            Transaction transaction = commonCreationPart(transactionSnapshot);
            transaction.setTransactionType(TransactionType.OUTCOME);
            transaction.setSourceAccountId(transactionSnapshot.getSourceAccountId());
            return transaction;
        }

        static Transaction createReceiptTransaction(TransactionSnapshot transactionSnapshot) {
            Transaction transaction = commonCreationPart(transactionSnapshot);
            transaction.setTransactionType(TransactionType.RECEIPT);
            transaction.setSourceAccountId(transactionSnapshot.getSourceAccountId());
            transaction.setReceiptId(transactionSnapshot.getReceiptId());
            return transaction;
        }

        static Transaction createTakenLoanTransaction(TransactionSnapshot transactionSnapshot) {
            Transaction transaction = commonCreationPart(transactionSnapshot);
            transaction.setTransactionType(TransactionType.TAKEN_LOAN);
            transaction.setTargetAccountId(transactionSnapshot.getTargetAccountId());;
            transaction.setRepaymentDate(transactionSnapshot.getRepaymentDate());
            return transaction;
        }

        static Transaction createGivenLoanTransaction(TransactionSnapshot transactionSnapshot) {
            Transaction transaction = commonCreationPart(transactionSnapshot);
            transaction.setTransactionType(TransactionType.GIVEN_LOAN);
            transaction.setSourceAccountId(transactionSnapshot.getSourceAccountId());;
            transaction.setRepaymentDate(transactionSnapshot.getRepaymentDate());
            return transaction;
        }

        static Transaction createInnerTransferTransaction(TransactionSnapshot transactionSnapshot) {
            Transaction transaction = commonCreationPart(transactionSnapshot);
            transaction.setTransactionType(TransactionType.INNER_TRANSFER);
            transaction.setSourceAccountId(transactionSnapshot.getSourceAccountId());
            transaction.setTargetAccountId(transactionSnapshot.getTargetAccountId());
            return transaction;
        }

        static private Transaction commonCreationPart(TransactionSnapshot transactionSnapshot) {
            return new Transaction(transactionSnapshot.getId(),
                    transactionSnapshot.getIssuedOnDateTime(),
                    transactionSnapshot.getTotalValue(),
                    transactionSnapshot.getTransactionCategoryId(),
                    transactionSnapshot.getDescription());
        }
    }
}





