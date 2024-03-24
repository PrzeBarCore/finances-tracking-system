package io.github.PrzeBarCore.Product;

import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.SimpleCategoryDto;
import io.github.PrzeBarCore.ValueObjects.Company;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;
import io.github.PrzeBarCore.ValueObjects.Unit;

import java.util.Optional;

public class ProductDto extends SimpleProductDto{
    private Double quantity;
    private Unit unit;
    private SimpleCategoryDto productCategory;

    public ProductDto(Integer id, NameString name, Company producer, Double quantity, Unit unit, SimpleCategoryDto productCategory, MonetaryAmount defaultPrice, SimpleCategoryDto defaultReceiptTransactionCategory) {
        super(id,name,producer,defaultPrice,defaultReceiptTransactionCategory);
        this.quantity = quantity;
        this.unit = unit;
        this.productCategory = productCategory;
    }

    public Integer getId() {
        return super.getId();
    }

    void setId(Integer id) {
        super.setId(id);
    }

    NameString getName() {
        return super.getName();
    }

    void setName(NameString name) {
        super.setName(name);
    }

    Company getProducer() {
        return super.getProducer();
    }

    void setProducer(Company producer) {
        super.setProducer(producer);
    }

    Double getQuantity() {
        return quantity;
    }

    void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    Unit getUnit() {
        return unit;
    }

    void setUnit(Unit unit) {
        this.unit = unit;
    }

    SimpleCategoryDto getProductCategory() {
        return productCategory;
    }

    void setProductCategory(final SimpleCategoryDto productCategory) {
        this.productCategory = productCategory;
    }

    MonetaryAmount getDefaultPrice() { return super.getDefaultPrice();}

    void setDefaultPrice(MonetaryAmount defaultPrice) { super.setDefaultPrice(defaultPrice); }

    SimpleCategoryDto getDefaultReceiptTransactionCategory() { return super.getDefaultReceiptTransactionCategory(); }

    void setDefaultReceiptTransactionCategory(SimpleCategoryDto defaultReceiptTransactionCategory) {super.setDefaultReceiptTransactionCategory(defaultReceiptTransactionCategory);}
}
