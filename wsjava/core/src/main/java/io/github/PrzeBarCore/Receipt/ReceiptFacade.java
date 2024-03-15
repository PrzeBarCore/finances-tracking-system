package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductDto;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Transaction.TransactionDto;
import io.github.PrzeBarCore.Transaction.TransactionFacade;
import io.github.PrzeBarCore.Transaction.TransactionFactory;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ReceiptFacade {
    private final ReceiptRepository repository;
    private final ReceiptFactory receiptFactory;
    private final ProductFacade productFacade;
    private final CategoryFacade categoryFacade;
    private final TransactionFacade transactionFacade;

    ReceiptFacade(final ReceiptRepository repository, final ReceiptFactory receiptFactory, final ProductFacade productFacade, final CategoryFacade categoryFacade, final TransactionFacade transactionFacade) {
        this.repository = repository;
        this.receiptFactory = receiptFactory;
        this.productFacade = productFacade;
        this.categoryFacade = categoryFacade;
        this.transactionFacade = transactionFacade;
    }

    public Optional<ReceiptDto> findReceipt(Integer id) {
        return repository.findById(id).map(receiptFactory::createDto);
    }

    public ReceiptDto  createReceipt(ReceiptDto receiptToCreate) {
//        for(ReceiptDto.DtoItem item : receiptToCreate.getItems()){
//            if(item.getProduct().isPresent()) {
//                ProductDto product = item.getProduct().get();
//                if (null!=product.getId() && product.getId() == 0) {
//                    item.setProduct(Optional.of(productFacade.createProduct(product)));
//                }
//            }
//            CategoryDto expenseCategory = item.getExpenseCategory();
//            if(null == expenseCategory)
//                throw new IllegalArgumentException("Expense category should not be null");
//            if(null != expenseCategory.getId() && expenseCategory.getId() == 0){
//                //item.setExpenseCategory(categoryFacade.createCategory(expenseCategory));
//            }
//        }
        ReceiptDto createdReceipt = receiptFactory.createDto(repository.save(receiptFactory.createEntity(receiptToCreate)));
        this.transactionFacade.addTransaction(new TransactionDto(0,
                createdReceipt.getIssuedOnDateTime(),
                createdReceipt.getTotalValue(),
                TransactionType.RECEIPT.toString(),
                "",
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.of(receiptToCreate.getSourceAccount()),
                Optional.of(createdReceipt)
        ));
                return createdReceipt;
    }

}
