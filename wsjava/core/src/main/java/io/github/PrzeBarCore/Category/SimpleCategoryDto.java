package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Set;

public class SimpleCategoryDto {

    private Integer id;
    private NameString name;
    private CategoryType categoryType;


    SimpleCategoryDto(Integer id, NameString name, CategoryType categoryType) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    NameString getName() {
        return name;
    }

    void setName(NameString name) {
        this.name = name;
    }

    CategoryType getCategoryType() {
        return categoryType;
    }

    void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
