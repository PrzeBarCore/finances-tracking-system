package io.github.PrzeBarCore.Transaction;

import java.util.List;
import java.util.Optional;

interface TransactionRepository {

    boolean existsTransactionById(int id);

    Optional<Transaction> find(int id);

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findTransactionsByAccountIdOrderByIssuedondatetime(int id);
}
