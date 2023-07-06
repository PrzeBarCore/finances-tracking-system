package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Transaction.Dto.TransactionCategoryDto;
import io.github.PrzeBarCore.Transaction.Dto.TransactionDto;

public class TransactionFactory {
//    public static TransactionDto createTransactionFromSnapshot(TransactionSnapshot transactionSnapshot){
//        return TransactionDto.builder().withId(transactionSnapshot.getId())
//                .withAccountId(transactionSnapshot.getAccountId())
//                .withIssuedOnDateTime(transactionSnapshot.getIssuedOnDateTime())
//                .withTotalValue(transactionSnapshot.getTotalValue())
//                .withTransactionCategory(createCategoryFromSnapshot(transactionSnapshot.getTransactionCategory()))
//                .withTransactionType(transactionSnapshot.getTransactionType())
//                .build();
//    }
//
//    public static TransactionCategoryDto createCategoryFromSnapshot(TransactionCategorySnapshot categorySnapshot){
//        if(null!=categorySnapshot) {
//            return TransactionCategoryDto.builder().withId(categorySnapshot.getId())
//                    .withName(categorySnapshot.getName())
//                    .withDependencyLevel(categorySnapshot.getDependencyLevel())
//                    .withParentCategory(createCategoryFromSnapshot(categorySnapshot.getParentCategory()))
//                    .build();
//        }
//        return null;
//    }
}
