package io.github.PrzeBarCore.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionFacade {
    TransactionRepository transactionRepository;

    TransactionFacade(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionDto addTransaction(final TransactionDto transactionDto) {
        if(!transactionRepository.existsTransactionById(transactionDto.getId())){
            return TransactionFactory.dtoFromTransaction(transactionRepository.saveTransaction(TransactionFactory.restoreTransactionFromDto(transactionDto)));
        }
        else
            return transactionDto;
    }

    public List<TransactionDto> findTransactionsByAccountId(final int id) {
        return transactionRepository.findTransactionsByAccountIdOrderByIssuedOnDateTime(id).stream()
                .map(transaction -> TransactionFactory.dtoFromSnapshot(transaction.getSnapshot()))
                .collect(Collectors.toList());

    }

    public TransactionDto find(final int id){
        return transactionRepository.find(id)
                .map(transaction -> TransactionFactory.dtoFromSnapshot(transaction.getSnapshot()))
                .orElseGet(null);
    }


}
