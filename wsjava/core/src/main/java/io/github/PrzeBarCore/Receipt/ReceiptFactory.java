package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;

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
        return new ReceiptSnapshot(0,
                dto.getIssuedOnDateTime(),
                MonetaryAmount.of(dto.getTotalValue()),
                dto.isContainingListOfItems(),
                dto.getItems().stream()
                        .map(item -> new ReceiptItemSnapshot(0,
                                item.getName(),
                                item.getProduct().map(ProductDto::getId).orElse(null),
                                item.getQuantity(),
                                MonetaryAmount.of(item.getRegularPrice()),
                                MonetaryAmount.of(item.getDiscount()),
                                item.getExpenseCategory().getId()))
                        .collect(toList()));
    }

    private ReceiptDto createDto(ReceiptSnapshot snapshot){
        return new ReceiptDto(snapshot.getIssuedOnDateTime(),
                snapshot.getTotalValue().toBigDecimal(),
                snapshot.isContainingListOfItems(),
                snapshot.getItems().stream()
                        .map(item -> new ReceiptDto.DtoItem(item.getName(),
                                productFacade.findProduct(item.getProductId()),
                                item.getQuantity(),
                                item.getRegularPrice().toBigDecimal(),
                                item.getDiscount().toBigDecimal(),
                                categoryFacade.findCategoryById(item.getExpenseCategoryId()).orElseThrow(IllegalArgumentException::new)))
                        .collect(toList()));
    }

}
