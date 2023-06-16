package io.github.PrzeBarCore.Transaction;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

interface TransactionRepository {

    boolean existsTransactionById(int id);
    Optional<Transaction> findTransactionOfIncomeType(int id);
    Optional<Transaction> findTransactionOfOutcomeType(int id);
    Optional<Transaction> findTransactionOfReceiptType(int id);
    Optional<Transaction> findTransactionOfTakenLoanType(int id);
    Optional<Transaction> findTransactionOfGivenLoanType(int id);
    Optional<Transaction> findTransactionOfInnerTransferType(int id);
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> findTransactionsByAccountIdOrderByIssuedOnDateTime(int id);
//temporary
    Optional<Transaction> find(int id);
}
