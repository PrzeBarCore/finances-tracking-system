package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Set;

public class CategoryDto extends SimpleCategoryDto{
    private Set<CategoryDto> childCategories;
    private CategoryDto parentCategory;
    private Boolean hasAnyChild;

    public CategoryDto(Integer id, NameString name, CategoryType categoryType, Set<CategoryDto> childCategories, CategoryDto parentCategory) {
        super(id, name, categoryType);
        this.childCategories = childCategories;
        this.parentCategory = parentCategory;
        this.hasAnyChild = null != this.childCategories && !this.childCategories.isEmpty();
    }

    public Integer getId() {
        return super.getId();
    }

    void setId(Integer id) {
        super.setId(id);
    }

    NameString getName() {
        return super.getName();
    }

    void setName(NameString name) {
        super.setName(name);
    }

    CategoryType getCategoryType() {
        return super.getCategoryType();
    }

    void setCategoryType(CategoryType categoryType) {
        super.setCategoryType(categoryType);
    }

    Set<CategoryDto> getChildCategories() {
        return childCategories;
    }

    void setChildCategories(Set<CategoryDto> childCategories) {
        this.childCategories = childCategories;
    }
    CategoryDto getParentCategory() {
        return parentCategory;
    }
    void setParentCategory(CategoryDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    Boolean getHasAnyChild() {
        return hasAnyChild;
    }

    void setHasAnyChild(Boolean hasAnyChild) {
        this.hasAnyChild = hasAnyChild;
    }
}

