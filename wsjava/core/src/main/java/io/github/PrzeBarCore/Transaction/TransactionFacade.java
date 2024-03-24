package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionFacade {
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;
    private final ReceiptFacade receiptFacade;

    TransactionFacade(TransactionRepository transactionRepository, AccountFacade accountFacade, ReceiptFacade receiptFacade, CategoryFacade categoryFacade) {
        this.transactionRepository = transactionRepository;
        this.receiptFacade = receiptFacade;
        this.transactionFactory = new TransactionFactory(accountFacade, receiptFacade, categoryFacade);
    }

    public SimpleTransactionDto addTransaction(final SimpleTransactionDto simpleTransactionDto) {
        if(!transactionRepository.existsTransactionById(simpleTransactionDto.getId())){
            return transactionFactory.createDto(transactionRepository.save(transactionFactory.createEntity(simpleTransactionDto)));
        }
        else
            return simpleTransactionDto;
    }

    public SimpleTransactionDto updateTransaction(final SimpleTransactionDto simpleTransactionDto) {
        if(transactionRepository.existsTransactionById(simpleTransactionDto.getId())){
            return transactionFactory.createDto(transactionRepository.save(transactionFactory.createEntity(simpleTransactionDto)));
        }
        else
            return simpleTransactionDto;
    }

    public boolean deleteTransaction(Integer id) {
        Optional<SimpleTransactionDto> transactionToRemove= findTransaction(id);
        if(transactionToRemove.isPresent()){
            if(transactionToRemove.get().getTransactionType() == TransactionType.RECEIPT){
                receiptFacade.deleteReceipt(transactionToRemove.map(transactionDto -> (ReceiptTypeSimpleTransactionDto)transactionDto).get().getReceiptId());
            }
            return transactionRepository.deleteTransactionById(id);
        }
        else
            return false;
    }

    public Optional<SimpleTransactionDto> findTransaction(Integer id){
        return transactionRepository.findTransactionById(id).map(transactionFactory::createDto);
    }

    public List<SimpleTransactionDto> findTransactionsWithAccountId(int accountId) {
        return transactionRepository.findTransactionsByAccountId(accountId).stream().map(transactionFactory::createDto).collect(Collectors.toList());
    }
}
