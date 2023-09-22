package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFacade {
    private final ProductRepository repository;

    ProductFacade(final ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<ProductDto> findProduct(Integer id) {
        if(null !=id)
            return repository.findById(id).map(product -> createFromSnapshot(product.getSnapshot()));
        else
            return Optional.empty();
    }

    Set<ProductDto> findProductSet(NameString name, Company producer){
        return repository.findByNameContainingAndProducerContaining(name, producer )
                .stream()
                .map(product -> createFromSnapshot(product.getSnapshot()))
                .collect(Collectors.toSet());
    }

    public ProductDto createProduct(ProductDto productToCreate) {
        return createFromSnapshot(repository.save(Product.restore(createFromDto(productToCreate))).getSnapshot());
    }


    private ProductSnapshot createFromDto(ProductDto dto){
        return new ProductSnapshot(0,
                NameString.of(dto.getName()),
                Company.of(dto.getProducer()),
                dto.getQuantity(),
                dto.getUnit());
    }

    private ProductDto createFromSnapshot(ProductSnapshot snapshot){
        return new ProductDto(snapshot.getId(),
                snapshot.getName().getText(),
                snapshot.getProducer().getText(),
                snapshot.getQuantity(),
                snapshot.getUnit());
    }

}
