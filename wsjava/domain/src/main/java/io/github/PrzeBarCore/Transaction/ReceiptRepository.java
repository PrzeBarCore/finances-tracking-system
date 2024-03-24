package io.github.PrzeBarCore.Transaction;

import java.util.Optional;

interface ReceiptRepository {
    Receipt save(Receipt receipt);
    Optional<Receipt> findById(int id);
    boolean existsReceiptById(Integer receiptId);
    boolean deleteReceiptById(Integer receiptId);
}

