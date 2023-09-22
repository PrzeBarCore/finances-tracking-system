package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReceiptDto {
    private LocalDateTime issuedOnDateTime;
    private BigDecimal totalValue;
    private boolean isContainingListOfItems;
    private List<DtoItem> items;

    public ReceiptDto(LocalDateTime issuedOnDateTime, BigDecimal totalValue, boolean isContainingListOfItems, List<DtoItem> items) {
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.isContainingListOfItems = isContainingListOfItems;
        this.items = items;
    }

    public LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    public void setIssuedOnDateTime(LocalDateTime issuedOnDateTime) {
        this.issuedOnDateTime = issuedOnDateTime;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public boolean isContainingListOfItems() {
        return isContainingListOfItems;
    }

    public void setContainingListOfItems(boolean containingListOfItems) {
        isContainingListOfItems = containingListOfItems;
    }

    public List<DtoItem> getItems() {
        return items;
    }

    public void setItems(List<DtoItem> items) {
        this.items = items;
    }

    public static class DtoItem{
        private NameString name;
        private Optional<ProductDto> product;
        private Double quantity;
        private BigDecimal regularPrice;
        private BigDecimal discount;
        private Integer productCategoryId;

        public DtoItem(NameString name, Optional<ProductDto> product, Double quantity, BigDecimal regularPrice, BigDecimal discount, Integer productCategoryId) {
            this.name = name;
            this.product = product;
            this.quantity = quantity;
            this.regularPrice = regularPrice;
            this.discount = discount;
            this.productCategoryId = productCategoryId;
        }

        public NameString getName() {
            return name;
        }

        public void setName(NameString name) {
            this.name = name;
        }

        public Optional<ProductDto> getProduct() {
            return product;
        }

        public void setProduct(Optional<ProductDto> product) {
            this.product = product;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getRegularPrice() {
            return regularPrice;
        }

        public void setRegularPrice(BigDecimal regularPrice) {
            this.regularPrice = regularPrice;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public Integer getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(Integer productCategoryId) {
            this.productCategoryId = productCategoryId;
        }
    }
}
