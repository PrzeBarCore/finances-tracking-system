package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.NameString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(path = "/products")
class ProductController {

    private ProductFacade facade;

    ProductController(ProductFacade facade) {
        this.facade = facade;
    }
    @GetMapping
    ResponseEntity<Set<ProductDto>> findBySearchCriteria(@RequestParam(name = "name") String name, @RequestParam(name = "producer") String producer){
        return ResponseEntity.ok(new HashSet<>(facade.findProductSet(NameString.of(name), Company.of(producer))));
    }
    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productToCreate) {
        return ResponseEntity.ok(facade.createProduct(productToCreate));
    }
}
