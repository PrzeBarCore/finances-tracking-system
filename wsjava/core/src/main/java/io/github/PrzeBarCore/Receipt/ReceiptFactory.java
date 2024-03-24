package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Product.SimpleProductDto;
import io.github.PrzeBarCore.Transaction.ReceiptTypeSimpleTransactionDto;
import io.github.PrzeBarCore.Transaction.TransactionFacade;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import static java.util.stream.Collectors.toList;

public class ReceiptFactory {
    private final ProductFacade productFacade;
    private final CategoryFacade categoryFacade;
    private final TransactionFacade transactionFacade;

    ReceiptFactory(final ProductFacade productFacade, final CategoryFacade categoryFacade,final TransactionFacade transactionFacade) {
        this.productFacade = productFacade;
        this.categoryFacade = categoryFacade;
        this.transactionFacade = transactionFacade;
    }

    ReceiptDto createDto(Receipt receipt){
        return createDto(receipt.getSnapshot());
    }
    Receipt createEntity(ReceiptDto dto){
        return Receipt.restore(createSnapshot(dto));
    }

    private ReceiptSnapshot createSnapshot(ReceiptDto dto){
        return new ReceiptSnapshot(dto.getId(),
                dto.getTotalDiscount(),
                dto.getRelatedTransaction().getId(),
                dto.getItems().stream()
                        .map(item -> new ReceiptItemSnapshot(item.getId(),
                                item.getName(),
                                item.getQuantity(),
                                item.getRegularPrice(),
                                item.getDiscount(),
                                item.getProduct().map(SimpleProductDto::getId).orElse(null),
                                item.getExpenseCategory().getId()))
                        .collect(toList()));
    }


    private ReceiptDto createDto(ReceiptSnapshot snapshot){
        return new ReceiptDto(
                snapshot.getId(),
                snapshot.getTotalDiscount(),
                (ReceiptTypeSimpleTransactionDto) transactionFacade.findTransaction(snapshot.getRelatedTransactionId()).orElseThrow(IllegalArgumentException::new),
                snapshot.getItems().stream()
                        .map(item -> new ReceiptDto.DtoItem(item.getId(),
                                item.getName(),
                                item.getQuantity(),
                                item.getRegularPrice(),
                                item.getDiscount(),
                                productFacade.findSimpleProduct(item.getProductId()),
                                categoryFacade.findCategoryById(item.getExpenseCategoryId()).orElseThrow(IllegalArgumentException::new)))
                        .collect(toList()));
    }
}
