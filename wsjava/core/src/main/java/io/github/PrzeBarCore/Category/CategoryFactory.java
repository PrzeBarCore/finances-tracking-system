package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryFactory {
    static Category createEntity(CategoryDto categoryDto) {
        return Category.restore(createSnapshot(categoryDto));
    }
    static CategoryDto createDto(Category category){
        return createDto(category.getSnapshot());
    }
    private static CategorySnapshot createSnapshot(CategoryDto categoryDto){
        return new CategorySnapshot(categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getCategoryType(),
                null != categoryDto.getChildCategories() ?
                    categoryDto.getChildCategories().stream().map(CategoryFactory::createSnapshot).collect(Collectors.toSet()) :
                        new HashSet<>(), categoryDto.getParentCategory() != null ? createSnapshot(categoryDto.getParentCategory()) : null);
    }
    private static CategoryDto createDto(CategorySnapshot snapshot){
        return new CategoryDto(snapshot.getId(),
                snapshot.getName(),
                snapshot.getCategoryType(),
                snapshot.getChildCategories().stream().map(CategoryFactory::createDto).collect(Collectors.toSet()),
                snapshot.getParentCategory() != null ? createDto(snapshot.getParentCategory()) : null);
    }
}
