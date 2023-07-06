package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Transaction.Dto.TransactionCategoryDto;
import io.github.PrzeBarCore.Transaction.Dto.TransactionDto;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionFacade {
    TransactionRepository transactionRepository;

//    TransactionFacade(TransactionRepository transactionRepository) {
//        this.transactionRepository = transactionRepository;
//    }
//
//    public void addTransaction(final TransactionDto transactionDto) {
//        if(!transactionRepository.existsTransactionById(transactionDto.getId()))
//            transactionRepository.saveTransaction(Transaction.restore(transactionSnapshotFromDto(transactionDto)));
//    }
//
//    public List<TransactionDto> findTransactionsByAccountId(final int id) {
//        return transactionRepository.findTransactionsByAccountIdOrderByIssuedOnDateTime(id).stream()
//                .map(transaction -> TransactionSnapshotFactory.createTransactionFromSnapshot(transaction.getSnapshot()))
//                .collect(Collectors.toList());
//
//    }
//
//    public TransactionDto find(final int id){
//        return transactionRepository.find(id)
//                .map(transaction -> TransactionSnapshotFactory.createTransactionFromSnapshot(transaction.getSnapshot()))
//                .orElseGet(null);
//    }
//
//    private TransactionSnapshot transactionSnapshotFromDto(TransactionDto transactionDto){
//        if(null!=transactionDto){
//            return new TransactionSnapshot(transactionDto.getId(),
//                    transactionDto.getIssuedOnDateTime(),
//                    transactionDto.getTotalValue(),
//                    transactionDto.getTransactionType(),
//                    transactionCategorySnapshotFromDto(transactionDto.getTransactionCategory()),
//                    transactionDto.getAccountId() );
//        }
//        return null;
//
//    }
//
//    private TransactionCategorySnapshot transactionCategorySnapshotFromDto(TransactionCategoryDto transactionCategoryDto){
//        if(null!=transactionCategoryDto){
//            return new TransactionCategorySnapshot(transactionCategoryDto.getId(),
//                    transactionCategoryDto.getName(),
//                    transactionCategorySnapshotFromDto(transactionCategoryDto.getParentCategory()),
//                    transactionCategoryDto.getDependencyLevel());
//        }
//        return null;
//
//    }
}
