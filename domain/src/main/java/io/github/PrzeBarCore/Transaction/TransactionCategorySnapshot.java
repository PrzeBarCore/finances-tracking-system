package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.ValueObjects.NameString;

class TransactionCategorySnapshot {
        private Integer id;
        private NameString name;
        private TransactionCategorySnapshot parentCategory;
        private Integer dependencyLevel;

        protected TransactionCategorySnapshot(){};
        TransactionCategorySnapshot(Integer id, NameString name, TransactionCategorySnapshot parentCategory, Integer dependencyLevel) {
                this.id = id;
                this.name = name;
                this.parentCategory=parentCategory;
                this.dependencyLevel=dependencyLevel;
        }

        Integer getId() {
                return id;
        }
        NameString getName() {
                return name;
        }
        TransactionCategorySnapshot getParentCategory() { return this.parentCategory; }
        Integer getDependencyLevel() { return dependencyLevel; }
}
