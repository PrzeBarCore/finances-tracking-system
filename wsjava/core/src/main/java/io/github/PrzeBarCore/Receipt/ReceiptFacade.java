package io.github.PrzeBarCore.Receipt;

import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Transaction.ReceiptTypeSimpleTransactionDto;
import io.github.PrzeBarCore.Transaction.TransactionFacade;

import java.util.Optional;

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

    public Optional<ReceiptDto> findReceiptWithTransaction(Integer id) {
        return repository.findByRelatedTransactionId(id).map(receiptFactory::createDto);
    }

    public boolean  createReceipt(ReceiptDto receiptToCreate) {
        receiptToCreate.setRelatedTransaction((ReceiptTypeSimpleTransactionDto) transactionFacade.addTransaction(receiptToCreate.getRelatedTransaction()));
        ReceiptDto createdReceipt = receiptFactory.createDto(repository.save(receiptFactory.createEntity(receiptToCreate)));
        createdReceipt.getRelatedTransaction().setReceiptId(createdReceipt.getId());
        transactionFacade.updateTransaction(createdReceipt.getRelatedTransaction());
        return true;
    }

    public boolean deleteReceipt(Integer receiptId) {
        if(repository.existsReceiptById(receiptId)){
            repository.deleteReceiptById(receiptId);
            return true;
        }
        return false;
    }
}
