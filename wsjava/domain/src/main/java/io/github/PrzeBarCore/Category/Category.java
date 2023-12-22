package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Category {
    static Category restore(CategorySnapshot categorySnapshot) {
        return new Category(categorySnapshot.getId(),
                categorySnapshot.getName(),
                categorySnapshot.getCategoryType(),
                categorySnapshot.getChildCategories().stream().map(Category::restore).collect(Collectors.toSet()),
                categorySnapshot.getParentCategory() != null ? restoreWithoutChildren(categorySnapshot.getParentCategory()) : null);
    }

     private static Category restoreWithoutChildren(CategorySnapshot categorySnapshot) {
        return new Category(categorySnapshot.getId(),
                categorySnapshot.getName(),
                categorySnapshot.getCategoryType(),
                new HashSet<>(),
                categorySnapshot.getParentCategory() != null ? restoreWithoutChildren(categorySnapshot.getParentCategory()) : null);
    }

    private Integer id;
    private NameString name;
    private CategoryType categoryType;
    private Set<Category> childCategories ;
    private Category parentCategory;

    private Category(Integer id, NameString name, CategoryType categoryType, Set<Category> childCategories, Category parentCategory) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
        this.childCategories = childCategories;
        this.parentCategory = parentCategory;
    }

    boolean addCategory(Category categoryToAdd){
        if(!isSetContainingCategory(categoryToAdd.childCategories)){
            this.childCategories.add(categoryToAdd);
            return true;
        }
            return false;
    }

     boolean isSetContainingCategory(Set<Category> setToSearch){
        return isSetContainingCategory(setToSearch, this);
    }

    private boolean isSetContainingCategory(Set<Category> setToSearch, Category categoryToCheck){
        boolean result = false;
        if(null != setToSearch && !setToSearch.isEmpty()){
            result = setToSearch.contains(categoryToCheck);
            result |= setToSearch.stream().anyMatch(category -> isSetContainingCategory(category.childCategories, categoryToCheck));
        }
        return result;
    }

    CategorySnapshot getSnapshot() {
        return new CategorySnapshot(this.id, this.name, this.categoryType, this.childCategories.stream().map(Category::getSnapshot).collect(Collectors.toSet()), this.parentCategory != null ? parentCategory.getSnapshot() : null);
    }

}
