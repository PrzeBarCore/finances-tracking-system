package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.Category.CategoryDto;

import java.util.Optional;

public class ProductDto {
    private Integer id;
    private String name;
    private String producer;
    private Double quantity;
    private String unit;
    private CategoryDto productCategory;
    private Optional<CategoryDto> defaultExpenseCategory;

    public ProductDto(Integer id, String name, String producer, Double quantity, String unit, CategoryDto productCategory, Optional<CategoryDto> defaultExpenseCategory) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        this.unit = unit;
        this.productCategory = productCategory;
        this.defaultExpenseCategory = defaultExpenseCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public CategoryDto getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(final CategoryDto productCategory) {
        this.productCategory = productCategory;
    }

    public Optional<CategoryDto> getDefaultExpenseCategory() {
        return defaultExpenseCategory;
    }

    public void setDefaultExpenseCategory(final Optional<CategoryDto> defaultExpenseCategory) {
        this.defaultExpenseCategory = defaultExpenseCategory;
    }
}
