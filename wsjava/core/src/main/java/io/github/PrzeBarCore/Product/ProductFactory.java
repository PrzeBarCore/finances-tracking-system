package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

public class ProductFactory {

    private final CategoryFacade categoryFacade;

    ProductFactory(final CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    ProductDto createDto(Product product){
        return createDto(product.getSnapshot());
    }

    Product createEntity(ProductDto dto){
        return Product.restore(createSnapshot(dto));
    }

    private ProductSnapshot createSnapshot(ProductDto dto){
        return new ProductSnapshot(0,
                NameString.of(dto.getName()),
                Company.of(dto.getProducer()),
                dto.getQuantity(),
                dto.getUnit(),
                dto.getProductCategory().getId(),
                dto.getDefaultExpenseCategory().map(CategoryDto::getId).orElse(null));
    }

    private ProductDto createDto(ProductSnapshot snapshot){
        return new ProductDto(snapshot.getId(),
                snapshot.getName().getText(),
                snapshot.getProducer().getText(),
                snapshot.getQuantity(),
                snapshot.getUnit(),
                categoryFacade.findCategoryById(snapshot.getProductCategoryId()).orElseThrow(IllegalArgumentException::new),
                categoryFacade.findCategoryById(snapshot.getDefaultExpenseCategoryId()));
    }
}
