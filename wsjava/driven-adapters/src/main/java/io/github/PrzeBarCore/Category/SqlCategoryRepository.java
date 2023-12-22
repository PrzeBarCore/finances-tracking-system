package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

interface SqlCategoryRepository extends Repository <CategorySnapshot, Integer> {
    CategorySnapshot save(CategorySnapshot snapshot);
    Optional<CategorySnapshot> findById(Integer id);
    Optional<CategorySnapshot> findByNameAndCategoryType(NameString name, CategoryType categoryType);
    boolean existsById(Integer id);
    boolean existsByNameAndCategoryType(NameString name, CategoryType categoryType);
    Set<CategorySnapshot> findByCategoryTypeAndParentCategoryNull(CategoryType type);
    Set<CategorySnapshot> findByCategoryType(CategoryType type);
    @Modifying
    @Query(value = "delete from Categories where id = :id", nativeQuery = true)
    void deleteById(@Param("id") Integer id);


}
@org.springframework.stereotype.Repository
class CategoryRepositoryImpl implements CategoryRepository{
    final SqlCategoryRepository repository;

    CategoryRepositoryImpl(final SqlCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return Category.restore(repository.save(category.getSnapshot()));
    }
    @Override
    public Optional<Category> findById(Integer id) {
        return repository.findById(id).map(Category::restore);
    }
    @Override
    public Optional<Category> findByNameAndCategoryType(NameString name, CategoryType categoryType) {
        return repository.findByNameAndCategoryType(name, categoryType).map(Category::restore);
    }
    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
    @Override
    public boolean existsByNameAndCategoryType(NameString name, CategoryType categoryType) {
        return repository.existsByNameAndCategoryType(name,categoryType);
    }
    @Override
    public Set<Category> findByCategoryTypeAndParentCategoryNull(CategoryType type) {
        return repository.findByCategoryTypeAndParentCategoryNull(type).stream().map(Category::restore).collect(Collectors.toSet());
    }

    @Override
    public Set<Category> findByCategoryType(CategoryType type) {
        return repository.findByCategoryType(type).stream().map(Category::restore).collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
