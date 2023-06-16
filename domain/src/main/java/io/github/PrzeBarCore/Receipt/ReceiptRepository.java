package io.github.PrzeBarCore.Receipt;

import java.util.Optional;

interface ReceiptRepository {
    Receipt save(Receipt receipt);
    Optional<Receipt> findById(int id);
}

