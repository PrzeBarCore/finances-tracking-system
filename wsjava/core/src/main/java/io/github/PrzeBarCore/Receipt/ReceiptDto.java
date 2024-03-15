package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Product.ProductDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReceiptDto {
    private Integer id;
    private AccountDto sourceAccount;
    private LocalDateTime issuedOnDateTime;
    private Double totalValue;
    private Double totalDiscount;
    private List<DtoItem> items;

    ReceiptDto(){};
    public ReceiptDto(Integer id, LocalDateTime issuedOnDateTime, Double totalValue, Double totalDiscount, List<DtoItem> items) {
        this.id = id;
        this.issuedOnDateTime = issuedOnDateTime;
        this.totalValue = totalValue;
        this.totalDiscount = totalDiscount;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getIssuedOnDateTime() {
        return issuedOnDateTime;
    }

    public void setIssuedOnDateTime(LocalDateTime issuedOnDateTime) {
        this.issuedOnDateTime = issuedOnDateTime;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public List<DtoItem> getItems() {
        return items;
    }

    public void setItems(List<DtoItem> items) {
        this.items = items;

    }
    public AccountDto getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountDto sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public static class DtoItem{
        private Integer id;
        private String name;
        private Double quantity;
        private Double regularPrice;
        private Double discount;
        private Integer receiptId;
        private Optional<ProductDto> product;
        private CategoryDto category;

        public DtoItem(Integer id, String name, Double quantity, Double regularPrice, Double discount, Integer receiptId, Optional<ProductDto> product, CategoryDto category) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.regularPrice = regularPrice;
            this.discount = discount;
            this.receiptId = receiptId;
            this.product = product;
            this.category = category;
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

        public Double getRegularPrice() {
            return regularPrice;
        }

        public void setRegularPrice(Double regularPrice) {
            this.regularPrice = regularPrice;
        }

        public Double getDiscount() {
            return discount;
        }

        public void setDiscount(Double discount) {
            this.discount = discount;
        }

        public CategoryDto getExpenseCategory() {
            return category;
        }

        public void setExpenseCategory(CategoryDto category) {
            this.category = category;
        }

        public Integer getReceiptId() {
            return receiptId;
        }

        public void setReceiptId(Integer receiptId) {
            this.receiptId = receiptId;
        }
    }




}
