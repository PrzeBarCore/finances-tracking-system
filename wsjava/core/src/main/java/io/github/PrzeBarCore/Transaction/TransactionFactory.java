package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptDto;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.math.BigDecimal;
import java.util.Optional;

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
        return new TransactionDto(transactionSnapshot.getId(),
                transactionSnapshot.getIssuedOnDateTime(),
                transactionSnapshot.getTotalValue().getValue().doubleValue(),
                transactionSnapshot.getTransactionType().name(),
                transactionSnapshot.getDescription().getText(),
                transactionSnapshot.getRepaymentDate(),
                transactionSnapshot.getTransactionCategoryId() != null ?categoryFacade.findCategoryById(transactionSnapshot.getTransactionCategoryId()) : Optional.empty(),
                transactionSnapshot.getTargetAccountId() != null ? accountFacade.findAccount(transactionSnapshot.getTargetAccountId()) : Optional.empty(),
                transactionSnapshot.getSourceAccountId() != null ? accountFacade.findAccount(transactionSnapshot.getSourceAccountId()) : Optional.empty(),
                transactionSnapshot.getReceiptId() != null ? receiptFacade.findReceipt(transactionSnapshot.getReceiptId()) : Optional.empty());
        else
            return null;
    }

    private TransactionSnapshot snapshotFromDto(TransactionDto transactionDto){
        if(null!=transactionDto)
        return new TransactionSnapshot(transactionDto.getId(),
                transactionDto.getIssuedOnDateTime(),
                MonetaryAmount.of(BigDecimal.valueOf(transactionDto.getTotalValue())),
                TransactionType.valueOf(transactionDto.getTransactionType()),
                Description.of(transactionDto.getDescription()),
                transactionDto.getRepaymentDate(),
                transactionDto.getTransactionCategory().map(CategoryDto::getId).orElse(null),
                transactionDto.getTargetAccount().map(AccountDto::getId).orElse(null),
                transactionDto.getSourceAccount().map(AccountDto::getId).orElse(null),
                transactionDto.getReceipt().map(ReceiptDto::getId).orElse(null));
        else
            return null;
    }
}
