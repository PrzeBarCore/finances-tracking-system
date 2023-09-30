package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

class Category {
    static Category restore(CategorySnapshot categorySnapshot) {
        return new Category(categorySnapshot.getId(),
                categorySnapshot.getName(),
                null != categorySnapshot.getParentCategory() ?
                        Category.restore(categorySnapshot.getParentCategory()) : null,
                categorySnapshot.getCategoryType(),
                categorySnapshot.getDependencyLevel());
    }

    private Integer id;
    private NameString name;
    private Category parentCategory;
    private CategoryType categoryType;
    private Integer dependencyLevel;

    private Category(Integer id, NameString name, Category parentCategory, CategoryType categoryType,
                     Integer dependencyLevel) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.categoryType = categoryType;
        this.dependencyLevel = dependencyLevel;
    }

    CategorySnapshot getSnapshot() {
        return new CategorySnapshot(this.id, this.name, null == this.parentCategory ? null : this.parentCategory.getSnapshot(), this.categoryType, this.dependencyLevel);
    }

}
