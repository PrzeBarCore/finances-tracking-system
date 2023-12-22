package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Optional;
import java.util.Set;

interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Integer id);

    Optional<Category> findByNameAndCategoryType(NameString name, CategoryType categoryType) ;
    boolean existsById(Integer id);
    boolean existsByNameAndCategoryType(NameString name, CategoryType categoryType);
    Set<Category> findByCategoryTypeAndParentCategoryNull(CategoryType type);
    Set<Category> findByCategoryType(CategoryType type);
    void deleteById(Integer id);
}
