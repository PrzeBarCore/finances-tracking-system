package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

class TransactionCategory {
    static TransactionCategory restore(TransactionCategorySnapshot transactionCategorySnapshot) {
        return new TransactionCategory(transactionCategorySnapshot.getId(),
                transactionCategorySnapshot.getName(),
                null != transactionCategorySnapshot.getParentCategory() ?
                        TransactionCategory.restore(transactionCategorySnapshot.getParentCategory()) : null,
                transactionCategorySnapshot.getTransactionType(),
                transactionCategorySnapshot.getDependencyLevel());
    }

    private Integer id;
    private NameString name;
    private TransactionCategory parentCategory;
    private TransactionType transactionType;
    private Integer dependencyLevel;

    private TransactionCategory(Integer id, NameString name, TransactionCategory parentCategory, TransactionType transactionType,
                                Integer dependencyLevel) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.transactionType = transactionType;
        this.dependencyLevel = dependencyLevel;
    }

    TransactionCategorySnapshot getSnapshot() {
        return new TransactionCategorySnapshot(this.id, this.name, null == this.parentCategory ? null : this.parentCategory.getSnapshot(), this.transactionType, this.dependencyLevel);
    }

}
