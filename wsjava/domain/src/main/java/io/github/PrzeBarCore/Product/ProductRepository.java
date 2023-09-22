package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.Optional;
import java.util.Set;

interface ProductRepository {
    Product save(Product entity);
    Optional<Product> findById(Integer id);
    Set<Product> findByNameContainingAndProducerContaining(NameString name, Company producer);
    boolean existsById(Integer id);
    boolean delete(Product entity);
}
