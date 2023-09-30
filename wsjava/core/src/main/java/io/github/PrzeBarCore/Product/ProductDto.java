package io.github.PrzeBarCore.Product;

public class ProductDto {
    private Integer id;
    private String name;
    private String producer;
    private Double quantity;
    private String unit;
    private Integer productCategoryId;
    private Integer defaultExpenseCategoryId;

    public ProductDto(Integer id, String name, String producer, Double quantity, String unit, Integer productCategoryId, Integer defaultExpenseCategoryId) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        this.unit = unit;
        this.productCategoryId = productCategoryId;
        this.defaultExpenseCategoryId = defaultExpenseCategoryId;
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

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(final Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getDefaultExpenseCategoryId() {
        return defaultExpenseCategoryId;
    }

    public void setDefaultExpenseCategoryId(final Integer defaultExpenseCategoryId) {
        this.defaultExpenseCategoryId = defaultExpenseCategoryId;
    }
}
