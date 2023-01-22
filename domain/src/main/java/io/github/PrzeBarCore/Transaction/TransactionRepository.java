package io.github.PrzeBarCore.Transaction;

import java.util.List;

interface TransactionRepository {

    boolean existsTransactionById(int id);

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findTransactionsByAccountIdOrderByIssuedondatetime(int id);
}
