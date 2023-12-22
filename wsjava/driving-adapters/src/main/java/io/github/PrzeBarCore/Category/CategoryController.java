package io.github.PrzeBarCore.Category;

import io.github.PrzeBarCore.ValueObjects.CategoryType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
class CategoryController {
    final CategoryFacade facade;

    CategoryController(final CategoryFacade facade) {this.facade = facade;}

    @GetMapping(path = "/categories/types/{type}")
    ResponseEntity<Set<CategoryDto>> findCategoriesOfType(@PathVariable String type){
        return ResponseEntity.ok(facade.findRootCategoriesOfType(CategoryType.valueOf(type.toUpperCase())));
    }

    @GetMapping(path = "/categories/types/{type}/all")
    ResponseEntity<Set<CategoryDto>> findAllCategoriesOfType(@PathVariable String type){
        return ResponseEntity.ok(facade.findAllCategoriesOfType(CategoryType.valueOf(type.toUpperCase())));
    }

    @GetMapping(path = "/categories/{id}")
    ResponseEntity<CategoryDto> find(@PathVariable int id){
        return ResponseEntity.of(facade.findCategoryById(id));
    }

    @PostMapping(path = "/categories")
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryToCreate){
        return ResponseEntity.of(facade.createCategory(categoryToCreate));
    }
    @PutMapping(path = "/categories")
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryToUpdate){
        return ResponseEntity.of(facade.updateCategory(categoryToUpdate));
    }

    @DeleteMapping(path = "/categories/{id}")
    ResponseEntity deleteCategoryById(@PathVariable Integer id){
        if(facade.deleteCategoryById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
     
}
