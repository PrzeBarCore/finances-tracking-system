package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

class TransactionCategorySnapshot {
    private Integer id;
    private NameString name;
    private TransactionCategorySnapshot parentCategory;
    private TransactionType transactionType;
    private Integer dependencyLevel;

    protected TransactionCategorySnapshot() {
    }
    TransactionCategorySnapshot(Integer id, NameString name, TransactionCategorySnapshot parentCategory, TransactionType transactionType,
                                Integer dependencyLevel) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.transactionType = transactionType;
        this.dependencyLevel = dependencyLevel;
    }

    Integer getId() {
        return id;
    }

    NameString getName() {
        return name;
    }

    TransactionCategorySnapshot getParentCategory() {
        return this.parentCategory;
    }

    TransactionType getTransactionType() {
        return this.transactionType;
    }

    Integer getDependencyLevel() {
        return dependencyLevel;
    }


}
