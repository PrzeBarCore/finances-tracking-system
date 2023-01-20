package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.NameString;

class TransactionCategory {
    static TransactionCategory restore(TransactionCategorySnapshot transactionCategorySnapshot){
        return new TransactionCategory(transactionCategorySnapshot.getId(),
                transactionCategorySnapshot.getName(),
                TransactionCategory.restore(transactionCategorySnapshot.getParentCategory()),
                transactionCategorySnapshot.getDependencyLevel());
    }

    private Integer id;
    private NameString name;
    private TransactionCategory parentCategory;

    private Integer dependencyLevel;

    private TransactionCategory(Integer id, NameString name, TransactionCategory parentCategory, Integer dependencyLevel) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.dependencyLevel=dependencyLevel;
    }

    TransactionCategorySnapshot getSnapshot(){
        return new TransactionCategorySnapshot(this.id, this.name, this.parentCategory.getSnapshot(), this.dependencyLevel);
    }

    void updateParentCategory(TransactionCategory newParentCategory){
        if(newParentCategory.getSnapshot().getDependencyLevel()<4)
            this.parentCategory=newParentCategory;
        else
            throw new IllegalArgumentException();
    }
}
