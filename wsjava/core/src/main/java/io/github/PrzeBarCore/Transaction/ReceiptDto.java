package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Category.SimpleCategoryDto;
import io.github.PrzeBarCore.Product.SimpleProductDto;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.util.List;
import java.util.Optional;

public class ReceiptDto {
    private Integer id;
    private MonetaryAmount totalDiscount;
    private List<DtoItem> items;

    ReceiptDto(){};
    public ReceiptDto(Integer id, MonetaryAmount totalDiscount, List<DtoItem> items) {
        this.id = id;
        this.totalDiscount = totalDiscount;
        this.items = items;
    }

    Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    MonetaryAmount getTotalDiscount() {
        return totalDiscount;
    }

    void setTotalDiscount(MonetaryAmount totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    List<DtoItem> getItems() {
        return items;
    }

    void setItems(List<DtoItem> items) {
        this.items = items;
    }

    public static class DtoItem{
        private Integer id;
        private NameString name;
        private Double quantity;
        private MonetaryAmount regularPrice;
        private MonetaryAmount discount;
        private Optional<SimpleProductDto> product;
        private SimpleCategoryDto category;

        DtoItem(Integer id, NameString name, Double quantity, MonetaryAmount regularPrice, MonetaryAmount discount, Optional<SimpleProductDto> product, SimpleCategoryDto category) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.regularPrice = regularPrice;
            this.discount = discount;
            this.product = product;
            this.category = category;
        }
        Integer getId() {
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

        Optional<SimpleProductDto> getProduct() {
            return product;
        }

        void setProduct(Optional<SimpleProductDto> product) {
            this.product = product;
        }

        Double getQuantity() {
            return quantity;
        }

        void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        MonetaryAmount getRegularPrice() {
            return regularPrice;
        }

        void setRegularPrice(MonetaryAmount regularPrice) {
            this.regularPrice = regularPrice;
        }

        MonetaryAmount getDiscount() {
            return discount;
        }

        void setDiscount(MonetaryAmount discount) {
            this.discount = discount;
        }

        SimpleCategoryDto getExpenseCategory() {
            return category;
        }

        void setExpenseCategory(SimpleCategoryDto category) {
            this.category = category;
        }
    }




}
