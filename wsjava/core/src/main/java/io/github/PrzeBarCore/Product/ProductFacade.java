package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFacade {
    private final ProductRepository repository;
    private final ProductFactory factory;

    ProductFacade(final ProductRepository repository, final ProductFactory factory) {
        this.repository = repository;
        this.factory= factory;
    }

    public Optional<SimpleProductDto> findSimpleProduct(Integer id) {
        if(null !=id)
            return repository.findById(id).map(factory::createDto);
        else
            return Optional.empty();
    }

    public Optional<ProductDto> findProduct(Integer id) {
        if(null !=id)
            return repository.findById(id).map(factory::createDto);
        else
            return Optional.empty();
    }

    Set<ProductDto> findAll(){
        return repository.findAll()
                .stream()
                .map(factory::createDto)
                .collect(Collectors.toSet());
    }

    Set<ProductDto> findProductSet(NameString name, Company producer){
        return repository.findByNameContainingAndProducerContaining(name, producer )
                .stream()
                .map(factory::createDto)
                .collect(Collectors.toSet());
    }

    public ProductDto createProduct(ProductDto productToCreate) {
        return factory.createDto(repository.save(factory.createEntity(productToCreate)));
    }


    ProductDto updateProduct(Integer productId, ProductDto productToUpdate) {
        findProduct(productId).ifPresentOrElse(product -> repository.save(factory.createEntity(productToUpdate)),
                () -> {throw new IllegalArgumentException("Product with given id does not exist");}
        );
        return productToUpdate;
    }
}
