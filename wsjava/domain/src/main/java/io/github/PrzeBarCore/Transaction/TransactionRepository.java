package io.github.PrzeBarCore.Transaction;

import java.util.List;
import java.util.Optional;

interface TransactionRepository {

    boolean existsTransactionById(int id);
    Optional<Transaction> findTransactionById(int id);
    Optional<Transaction> findTransactionByReceiptId(int id);
    List<Transaction> findTransactionsByAccountId(int id);
    Transaction save(Transaction transaction);
    boolean deleteTransactionById(Integer id);
}
