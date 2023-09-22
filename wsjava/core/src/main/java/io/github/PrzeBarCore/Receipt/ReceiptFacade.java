package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ReceiptFacade {
    private final ReceiptRepository repository;
    private final ProductFacade productFacade;

    ReceiptFacade(final ReceiptRepository repository, ProductFacade productFacade) {
        this.repository = repository;
        this.productFacade = productFacade;
    }

    Optional<ReceiptDto> findReceipt(int id) {
        return repository.findById(id).map(receipt -> createFromSnapshot(receipt.getSnapshot()));
    }

    public ReceiptDto createReceipt(ReceiptDto receiptToCreate) {
        for(ReceiptDto.DtoItem item : receiptToCreate.getItems()){
            if(item.getProduct().isPresent()) {
                ProductDto product = item.getProduct().get();
                if (null!=product.getId() && product.getId() == 0) {
                    item.setProduct(Optional.of(productFacade.createProduct(product)));
                }
            }
        }
        return createFromSnapshot(repository.save(Receipt.restore(createFromDto(receiptToCreate))).getSnapshot());
    }

    private ReceiptSnapshot createFromDto(ReceiptDto dto){
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
                                item.getProductCategoryId()))
                        .collect(toList()));
        }

        private ReceiptDto createFromSnapshot(ReceiptSnapshot snapshot){
            return new ReceiptDto(snapshot.getIssuedOnDateTime(),
                    snapshot.getTotalValue().toBigDecimal(),
                    snapshot.isContainingListOfItems(),
                    snapshot.getItems().stream()
                            .map(item -> new ReceiptDto.DtoItem(item.getName(),
                                    productFacade.findProduct(item.getProductId()),
                                    item.getQuantity(),
                                    item.getRegularPrice().toBigDecimal(),
                                    item.getDiscount().toBigDecimal(),
                                    item.getProductCategoryId()))
                            .collect(toList()));
        }

}
