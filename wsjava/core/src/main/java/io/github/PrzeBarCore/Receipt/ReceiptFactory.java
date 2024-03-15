package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.NameString;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class ReceiptFactory {

    private final ProductFacade productFacade;
    private final CategoryFacade categoryFacade;

    ReceiptFactory(final ProductFacade productFacade, final CategoryFacade categoryFacade) {
        this.productFacade = productFacade;
        this.categoryFacade = categoryFacade;
    }

    ReceiptDto createDto(Receipt receipt){
        return createDto(receipt.getSnapshot());
    }
    Receipt createEntity(ReceiptDto dto){
        return Receipt.restore(createSnapshot(dto));
    }

    private ReceiptSnapshot createSnapshot(ReceiptDto dto){
        return new ReceiptSnapshot(dto.getId(),
                dto.getIssuedOnDateTime(),
                MonetaryAmount.of(BigDecimal.valueOf(dto.getTotalValue())),
                MonetaryAmount.of(BigDecimal.valueOf(dto.getTotalDiscount())),
                dto.getItems().stream()
                        .map(item -> new ReceiptItemSnapshot(item.getId(),
                                NameString.of(item.getName()),
                                item.getQuantity(),
                                MonetaryAmount.of(BigDecimal.valueOf(item.getRegularPrice())),
                                MonetaryAmount.of(BigDecimal.valueOf(item.getDiscount())),
                                dto.getId(),
                                item.getProduct().map(ProductDto::getId).orElse(null),
                                item.getExpenseCategory().getId()))
                        .collect(toList()));
    }


    private ReceiptDto createDto(ReceiptSnapshot snapshot){
        return new ReceiptDto(
                snapshot.getId(),
                snapshot.getIssuedOnDateTime(),
                snapshot.getTotalValue().toBigDecimal().doubleValue(),
                snapshot.getTotalDiscount().toBigDecimal().doubleValue(),
                snapshot.getItems().stream()
                        .map(item -> new ReceiptDto.DtoItem(item.getId(),
                                item.getName().getText(),
                                item.getQuantity(),
                                item.getRegularPrice().toBigDecimal().doubleValue(),
                                item.getDiscount().toBigDecimal().doubleValue(),
                                snapshot.getId(),
                                productFacade.findProduct(item.getProductId()),
                                categoryFacade.findCategoryById(item.getExpenseCategoryId()).orElseThrow(IllegalArgumentException::new)))
                        .collect(toList()));
    }
}
