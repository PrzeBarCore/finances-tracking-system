package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

class CategorySnapshot {
    private Integer id;
    private NameString name;
    private CategorySnapshot parentCategory;
    private CategoryType categoryType;
    private Integer dependencyLevel;

    protected CategorySnapshot() {
    }
    CategorySnapshot(Integer id, NameString name, CategorySnapshot parentCategory, CategoryType categoryType,
                     Integer dependencyLevel) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.categoryType = categoryType;
        this.dependencyLevel = dependencyLevel;
    }

    Integer getId() {
        return id;
    }

    NameString getName() {
        return name;
    }

    CategorySnapshot getParentCategory() {
        return this.parentCategory;
    }

    CategoryType getCategoryType() {
        return this.categoryType;
    }

    Integer getDependencyLevel() {
        return dependencyLevel;
    }


}
