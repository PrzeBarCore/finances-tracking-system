package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Set;

public class CategoryDto {

    private Integer id;
    private String name;
    private CategoryType categoryType;
    private Set<CategoryDto> childCategories;
    private CategoryDto parentCategory;
    private Boolean hasAnyChild;


    public CategoryDto(Integer id, String name, CategoryType categoryType, Set<CategoryDto> childCategories, CategoryDto parentCategory) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
        this.childCategories = childCategories;
        this.parentCategory = parentCategory;
        this.hasAnyChild = null != this.childCategories && !this.childCategories.isEmpty();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public Set<CategoryDto> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<CategoryDto> childCategories) {
        this.childCategories = childCategories;
    }
    public CategoryDto getParentCategory() {
        return parentCategory;
    }
    public void setParentCategory(CategoryDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Boolean getHasAnyChild() {
        return hasAnyChild;
    }

    public void setHasAnyChild(Boolean hasAnyChild) {
        this.hasAnyChild = hasAnyChild;
    }
}

