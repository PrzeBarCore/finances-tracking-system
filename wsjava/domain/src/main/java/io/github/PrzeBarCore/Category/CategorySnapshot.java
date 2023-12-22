package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Set;

class CategorySnapshot {
    private Integer id;
    private NameString name;
    private CategoryType categoryType;
    private Set<CategorySnapshot> childCategories;

    private CategorySnapshot parentCategory;

    protected CategorySnapshot() {
    }
    CategorySnapshot(Integer id, NameString name, CategoryType categoryType, Set<CategorySnapshot> childCategories,CategorySnapshot parentCategory) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
        this.childCategories = childCategories;
        this.parentCategory = parentCategory;
    }

    Integer getId() {
        return id;
    }

    NameString getName() {
        return name;
    }

    CategoryType getCategoryType() {
        return this.categoryType;
    }

    Set<CategorySnapshot> getChildCategories() {
        return childCategories;
    }

    CategorySnapshot getParentCategory() {
        return parentCategory;
    }
}
