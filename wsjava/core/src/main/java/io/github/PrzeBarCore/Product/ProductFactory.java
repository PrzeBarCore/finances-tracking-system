package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

public class ProductFactory {

    private final CategoryFacade categoryFacade;

    ProductFactory(final CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    public ProductDto createDto(Product product){
        return createDto(product.getSnapshot());
    }

    public Product createEntity(ProductDto dto){
        return Product.restore(createSnapshot(dto));
    }

    private ProductSnapshot createSnapshot(ProductDto dto){
        return new ProductSnapshot(dto.getId(),
                NameString.of(dto.getName()),
                Company.of(dto.getProducer()),
                dto.getQuantity(),
                dto.getUnit(),
                dto.getProductCategory() == null ? null : dto.getProductCategory().getId(),
                dto.getDefaultPrice(),
                dto.getDefaultReceiptTransactionCategory() == null ? null : dto.getDefaultReceiptTransactionCategory().getId()
                );
    }

    private ProductDto createDto(ProductSnapshot snapshot){
        return new ProductDto(snapshot.getId(),
                snapshot.getName().getText(),
                snapshot.getProducer().getText(),
                snapshot.getQuantity(),
                snapshot.getUnit(),
                snapshot.getProductCategoryId() == null ? null : categoryFacade.findCategoryById(snapshot.getProductCategoryId()).orElseThrow(IllegalArgumentException::new),
                snapshot.getDefaultPrice(),
                snapshot.getDefaultReceiptTransactionCategoryId() == null ? null : categoryFacade.findCategoryById(snapshot.getDefaultReceiptTransactionCategoryId()).orElseThrow(IllegalArgumentException::new)

        );
    }
}
