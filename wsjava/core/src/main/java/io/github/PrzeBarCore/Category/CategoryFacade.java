package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryFacade {
    private final CategoryRepository repository;

    CategoryFacade(CategoryRepository repository) {
        this.repository = repository;
    }

    public Optional<CategoryDto> findCategoryById(Integer id){
        return repository.findById(id).map(CategoryFactory::createDto);
    }

    public Set<CategoryDto> findRootCategoriesOfType(CategoryType type){
        return repository.findByCategoryTypeAndParentCategoryNull(type).stream().map(CategoryFactory::createDto).collect(Collectors.toSet());
    }

    public Set<CategoryDto> findAllCategoriesOfType(CategoryType type){
        return repository.findByCategoryType(type).stream().map(CategoryFactory::createDto).collect(Collectors.toSet());
    }

    public boolean existsCategory(CategoryDto category){
        boolean result = false;
        result = repository.existsById(category.getId());
        result |=repository.existsByNameAndCategoryType(category.getName(), category.getCategoryType());
        return result;
    }
    //TODO what should system do when category already exists?
    //find original and check fo orginal
    public Optional<CategoryDto> updateCategory(CategoryDto categoryToUpdate){
        List<CategoryDto> result= new ArrayList<>();
        repository.findById(categoryToUpdate.getId())
                .map(CategoryFactory::createDto)
                .ifPresent(
                (foundCategory) -> {
                    if(validateParent(categoryToUpdate)){
                        saveCategoryWithParent(categoryToUpdate);
                    } else {
                        saveCategory(categoryToUpdate);
                    }
                    result.add(categoryToUpdate);
                });
        return result.stream().findAny();

    }
//TODO what should system do when category already exists?
    public Optional<CategoryDto> createCategory(CategoryDto categoryToAdd){
        if(!existsCategory(categoryToAdd)){
            if(validateParent(categoryToAdd)){
                saveCategoryWithParent(categoryToAdd);
            } else {
                saveCategory(categoryToAdd);
            }
            return Optional.of(categoryToAdd);
        }
        return Optional.empty();
    }

    public boolean deleteCategoryById(Integer id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }


// is it good method?
    private CategoryDto saveCategoryWithParent(CategoryDto categoryToAdd){
            findCategoryById(categoryToAdd.getParentCategory().getId())
                    .map(CategoryFactory::createEntity)
                    .ifPresent((parent)-> {
                        if(parent.addCategory(CategoryFactory.createEntity(categoryToAdd))){
                            repository.save(parent);
                        }
                        else
                            throw new IllegalArgumentException("Cannot assign category to one of its subcategories!");
                    });
        return categoryToAdd;
    }

    private CategoryDto saveCategory(CategoryDto categoryDto){
        return CategoryFactory.createDto(repository.save(CategoryFactory.createEntity(categoryDto)));
    }

    private boolean validateParent(CategoryDto categoryToAdd) {
        if(null != categoryToAdd.getParentCategory()){
            Optional<CategoryDto> parentCategory = findCategoryById(categoryToAdd.getParentCategory().getId());
            if(parentCategory.isPresent())
                if(!parentCategory.map(CategoryFactory::createEntity)
                        .map(entity -> null != categoryToAdd.getChildCategories() && entity.isSetContainingCategory(categoryToAdd.getChildCategories()
                                .stream()
                                .map(CategoryFactory::createEntity)
                                .collect(Collectors.toSet())))
                        .orElse(false))
                    return true;
                else
                    throw new IllegalArgumentException("Cannot assign category to one of its subcategories!");
            else
                throw new IllegalArgumentException("Provided parent category does not exist!");
        }
        return false;
    }


}

