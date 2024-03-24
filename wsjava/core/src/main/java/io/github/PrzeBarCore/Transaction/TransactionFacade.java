package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionFacade {
    private final TransactionRepository transactionRepository;
    private final ReceiptRepository receiptRepository;
    private final TransactionFactory transactionFactory;


    TransactionFacade(TransactionRepository transactionRepository, ReceiptRepository receiptRepository, AccountFacade accountFacade, CategoryFacade categoryFacade, ProductFacade productFacade) {
        this.transactionRepository = transactionRepository;
        this.receiptRepository = receiptRepository;
        this.transactionFactory = new TransactionFactory(accountFacade, categoryFacade, productFacade);
    }

    public Optional<SimpleTransactionDto> findTransaction(Integer id){
        return transactionRepository.findTransactionById(id).map(transactionFactory::createTransactionDto);
    }

    public List<SimpleTransactionDto> findTransactionsWithAccountId(int accountId) {
        return transactionRepository.findTransactionsByAccountId(accountId).stream().map(transactionFactory::createTransactionDto).collect(Collectors.toList());
    }

    public SimpleTransactionDto addTransaction(final SimpleTransactionDto simpleTransactionDto) {
        if(!transactionRepository.existsTransactionById(simpleTransactionDto.getId())){
            return transactionFactory.createTransactionDto(transactionRepository.save(transactionFactory.createTransactionEntity(simpleTransactionDto)));
        }
        else
            return simpleTransactionDto;
    }


    public SimpleTransactionDto updateTransaction(final SimpleTransactionDto simpleTransactionDto) {
        if(transactionRepository.existsTransactionById(simpleTransactionDto.getId())){
            return transactionFactory.createTransactionDto(transactionRepository.save(transactionFactory.createTransactionEntity(simpleTransactionDto)));
        }
        else
            return simpleTransactionDto;
    }
    public boolean deleteTransaction(Integer id) {
        Optional<SimpleTransactionDto> transactionToRemove= findTransaction(id);
        if(transactionToRemove.isPresent()){
            return transactionRepository.deleteTransactionById(id);
        }
        else
            return false;
    }

    public boolean addReceiptTransaction(ReceiptTypeTransaction transaction) {
        if(!receiptRepository.existsReceiptById(transaction.getReceipt().getId())){
            addTransaction(transaction);
            return true;
        } else
            return false;
    }

    public Optional<ReceiptTypeTransaction> findReceiptTransaction(Integer id){
        return transactionRepository.findTransactionByReceiptId(id)
                .map(transaction -> (ReceiptTypeTransaction)transactionFactory.createTransactionDto((transaction)));
    }

    public boolean deleteReceipt(Integer receiptId) {
        if(receiptRepository.existsReceiptById(receiptId)){
            receiptRepository.deleteReceiptById(receiptId);
            return true;
        }
        return false;
    }
}
