package io.github.PrzeBarCore.Transaction;

public class TransactionFactory {

    static TransactionDto dtoFromSnapshot(TransactionSnapshot transactionSnapshot) {
        if(null !=transactionSnapshot)
        return TransactionDto.builder().withId(transactionSnapshot.getId())
                .withIssuedOnDateTime(transactionSnapshot.getIssuedOnDateTime())
                .withTotalValue(transactionSnapshot.getTotalValue())
                .withTransactionCategoryId(transactionSnapshot.getTransactionCategoryId())
                .withTransactionType(transactionSnapshot.getTransactionType())
                .withDescription(transactionSnapshot.getDescription())
                .withRepaymentDate(transactionSnapshot.getRepaymentDate())
                .withSourceAccountId(transactionSnapshot.getSourceAccountId())
                .withTargetAccountId(transactionSnapshot.getTargetAccountId())
                .withReceiptId(transactionSnapshot.getReceiptId())
                .build();
        else
            return null;
    }

    static TransactionSnapshot snapshotFromDto(TransactionDto transactionDto){
        if(null!=transactionDto)
        return new TransactionSnapshot(transactionDto.getId(),
                transactionDto.getIssuedOnDateTime(),
                transactionDto.getTotalValue(),
                transactionDto.getTransactionType(),
                transactionDto.getTransactionCategoryId(),
                transactionDto.getDescription(),
                transactionDto.getTargetAccountId(),
                transactionDto.getSourceAccountId(), transactionDto.getRepaymentDate(),
                transactionDto.getReceiptId() );
        else
            return null;
    }

    static Transaction restoreTransactionFromSnapshot(TransactionSnapshot transactionSnapshot){
        Transaction transaction;
        switch(transactionSnapshot.getTransactionType()){
            case GIVEN_LOAN:
                transaction = Transaction.Creator.createGivenLoanTransaction(transactionSnapshot);
            break;
            case TAKEN_LOAN:
                transaction =Transaction.Creator.createTakenLoanTransaction(transactionSnapshot);
                break;
            case INCOME:
                transaction =Transaction.Creator.createIncomeTransaction(transactionSnapshot);
                break;
            case OUTCOME:
                transaction =Transaction.Creator.createOutcomeTransaction(transactionSnapshot);
                break;
            case RECEIPT:
                transaction =Transaction.Creator.createReceiptTransaction(transactionSnapshot);
                break;
            case INNER_TRANSFER:
                transaction =Transaction.Creator.createInnerTransferTransaction(transactionSnapshot);
                break;
            default:
                transaction = null;
        }

        return transaction;
    }

    static Transaction restoreTransactionFromDto(TransactionDto transactionDto) {
        return restoreTransactionFromSnapshot(snapshotFromDto(transactionDto));
    }

    static TransactionDto dtoFromTransaction(Transaction transaction) {
        return dtoFromSnapshot(transaction.getSnapshot());
    }
}
