package io.github.PrzeBarCore.Product;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
interface SqlProductRepository extends Repository<ProductSnapshot, Integer> {

    ProductSnapshot save(ProductSnapshot entity);
    Optional<ProductSnapshot> findById(Integer id);
    @Query(value = "SELECT * FROM Products p WHERE p.name LIKE %:name% AND p.producer LIKE %:producer%", nativeQuery = true)
    Set<ProductSnapshot> findByNameContainingAndProducerContaining(@Param("name") String name, @Param("producer") String producer);
    Set<ProductSnapshot> findAll();
    boolean existsById(Integer id);
    boolean delete(ProductSnapshot entity);
}

@org.springframework.stereotype.Repository
class ProductRepositoryImpl implements ProductRepository{

    final SqlProductRepository repository;
    ProductRepositoryImpl(SqlProductRepository repository) {
        this.repository = repository;
    }
    @Override
    public Product save(Product entity) {
        return Product.restore(repository.save(entity.getSnapshot()));
    }
    @Override
    public Optional<Product> findById(Integer id) {
        return repository.findById(id).map(Product::restore);
    }
    @Override
    public Set<Product> findByNameContainingAndProducerContaining(NameString name, Company producer) {
        return repository.findByNameContainingAndProducerContaining(name.getText(),producer.getText()).stream()
                .map(Product::restore)
                .collect(Collectors.toSet());
    }

    public Set<Product> findAll() {
        return repository.findAll().stream()
                .map(Product::restore)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
    @Override
    public boolean delete(Product entity) {
        return repository.delete(entity.getSnapshot());
    }
}
