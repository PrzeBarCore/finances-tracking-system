package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.SimpleCategoryDto;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

public class SimpleProductDto {
    private Integer id;
    private NameString name;
    private Company producer;
    private MonetaryAmount defaultPrice;
    private SimpleCategoryDto defaultReceiptTransactionCategory;

    SimpleProductDto(Integer id, NameString name, Company producer, MonetaryAmount defaultPrice, SimpleCategoryDto defaultReceiptTransactionCategory) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.defaultPrice = defaultPrice;
        this.defaultReceiptTransactionCategory = defaultReceiptTransactionCategory;
    }

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    NameString getName() {
        return name;
    }

    void setName(NameString name) {
        this.name = name;
    }

    Company getProducer() {
        return producer;
    }

    void setProducer(Company producer) {
        this.producer = producer;
    }

    MonetaryAmount getDefaultPrice() { return defaultPrice;}

    void setDefaultPrice(MonetaryAmount defaultPrice) { this.defaultPrice = defaultPrice; }

    SimpleCategoryDto getDefaultReceiptTransactionCategory() { return defaultReceiptTransactionCategory; }

    void setDefaultReceiptTransactionCategory(SimpleCategoryDto defaultReceiptTransactionCategory) {this.defaultReceiptTransactionCategory = defaultReceiptTransactionCategory;}
}
