package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;

import java.util.Optional;
import java.util.stream.Collectors;

public class ReceiptFacade {
    private final ReceiptRepository repository;

    ReceiptFacade(final ReceiptRepository repository) {
        this.repository = repository;
    }

    Optional<ReceiptDto> findReceipt(int id) {
        return repository.findById(id).map(receipt -> createFromSnapshot(receipt.getSnapshot()));
    }

    ReceiptDto createReceipt(ReceiptDto receiptToCreate) {
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
                                item.getProductId(),
                                item.getQuantity(),
                                MonetaryAmount.of(item.getRegularPrice()),
                                MonetaryAmount.of(item.getDiscount()),
                                item.getProductCategoryId()))
                        .collect(Collectors.toList()));
        }

        private ReceiptDto createFromSnapshot(ReceiptSnapshot snapshot){
            return new ReceiptDto(snapshot.getIssuedOnDateTime(),
                    snapshot.getTotalValue().toBigDecimal(),
                    snapshot.isContainingListOfItems(),
                    snapshot.getItems().stream()
                            .map(item -> new ReceiptDto.DtoItem(item.getName(),
                                    item.getProductId(),
                                    item.getQuantity(),
                                    item.getRegularPrice().toBigDecimal(),
                                    item.getDiscount().toBigDecimal(),
                                    item.getProductCategoryId()))
                            .collect(Collectors.toList()));
        }

}
