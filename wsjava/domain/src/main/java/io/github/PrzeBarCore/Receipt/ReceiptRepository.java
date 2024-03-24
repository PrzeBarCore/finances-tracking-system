package io.github.PrzeBarCore.Receipt;

import java.util.List;
import java.util.Optional;

interface ReceiptRepository {
    Receipt save(Receipt receipt);
    Optional<Receipt> findById(int id);
    Optional<Receipt> findByRelatedTransactionId(int id);
    boolean existsReceiptById(Integer receiptId);
    boolean deleteReceiptById(Integer receiptId);
}

