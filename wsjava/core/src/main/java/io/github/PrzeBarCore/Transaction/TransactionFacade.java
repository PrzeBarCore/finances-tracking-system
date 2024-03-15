package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionFacade {
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;

    TransactionFacade(TransactionRepository transactionRepository, AccountFacade accountFacade, ReceiptFacade receiptFacade, CategoryFacade categoryFacade) {
        this.transactionRepository = transactionRepository;
        this.transactionFactory = new TransactionFactory(accountFacade, receiptFacade, categoryFacade);
    }

    public TransactionDto addTransaction(final TransactionDto transactionDto) {
        if(!transactionRepository.existsTransactionById(transactionDto.getId())){
            return transactionFactory.createDto(transactionRepository.save(transactionFactory.createEntity(transactionDto)));
        }
        else
            return transactionDto;
    }

    public List<TransactionDto> findTransactionsWithAccountId(int accountId) {
        return transactionRepository.findTransactionsByAccountId(accountId).stream().map(transactionFactory::createDto).collect(Collectors.toList());
    }
}
