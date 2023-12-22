package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptDto;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;

public class TransactionFactory {

    private final AccountFacade accountFacade;
    private final ReceiptFacade receiptFacade;
    private final CategoryFacade categoryFacade;

    TransactionFactory(AccountFacade accountFacade, ReceiptFacade receiptFacade, CategoryFacade categoryFacade) {
        this.accountFacade = accountFacade;
        this.receiptFacade = receiptFacade;
        this.categoryFacade = categoryFacade;
    }


    Transaction createEntity(TransactionDto transactionDto) {
        return Transaction.restore(snapshotFromDto(transactionDto));
    }

    TransactionDto createDto(Transaction transaction) {
        return dtoFromSnapshot(transaction.getSnapshot());
    }
     private TransactionDto dtoFromSnapshot(TransactionSnapshot transactionSnapshot) {
        if(null !=transactionSnapshot)
        return TransactionDto.builder().withId(transactionSnapshot.getId())
                .withIssuedOnDateTime(transactionSnapshot.getIssuedOnDateTime())
                .withTotalValue(transactionSnapshot.getTotalValue())
                .withTransactionCategory(categoryFacade.findCategoryById(transactionSnapshot.getTransactionCategoryId()))
                .withTransactionType(transactionSnapshot.getTransactionType())
                .withDescription(transactionSnapshot.getDescription())
                .withRepaymentDate(transactionSnapshot.getRepaymentDate())
                .withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()))
                .withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()))
                .withReceipt(receiptFacade.findReceipt(transactionSnapshot.getReceiptId()))
                .build();
        else
            return null;
    }

    private TransactionSnapshot snapshotFromDto(TransactionDto transactionDto){
        if(null!=transactionDto)
        return new TransactionSnapshot(transactionDto.getId(),
                transactionDto.getIssuedOnDateTime(),
                transactionDto.getTotalValue(),
                transactionDto.getTransactionType(),
                transactionDto.getTransactionCategory().map(CategoryDto::getId).orElse(null),
                transactionDto.getDescription(),
                transactionDto.getTargetAccount().map(AccountDto::getId).orElse(null),
                transactionDto.getSourceAccount().map(AccountDto::getId).orElse(null),
                transactionDto.getRepaymentDate(),
                transactionDto.getReceipt().map(ReceiptDto::getId).orElse(null));
        else
            return null;
    }
}
