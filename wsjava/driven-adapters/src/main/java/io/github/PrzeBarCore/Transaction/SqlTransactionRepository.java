package io.github.PrzeBarCore.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface SqlTransactionRepository extends Repository<TransactionSnapshot, Integer> {
    boolean existsTransactionById(int id);
    Optional<TransactionSnapshot> findTransactionById(int id);
    @Query(value ="SELECT * FROM transactions where source_account_id = :id OR target_account_id = :id", nativeQuery = true)
    List<TransactionSnapshot> findTransactionsByAccountId(@Param(value = "id") int id);
    @Query(value ="SELECT * FROM transactions where transaction_type = 'INCOME' AND id = :id ", nativeQuery = true)
    Optional<TransactionSnapshot> findTransactionOfIncomeType(@Param(value = "id") int id);
    @Query(value ="SELECT * FROM transactions where transaction_type = 'OUTCOME' AND id = :id ", nativeQuery = true)
    Optional<TransactionSnapshot> findTransactionOfOutcomeType(@Param(value = "id") int id);
    @Query(value ="SELECT * FROM transactions where transaction_type = 'RECEIPT' AND id = :id ", nativeQuery = true)
    Optional<TransactionSnapshot> findTransactionOfReceiptType(@Param(value = "id") int id);
    @Query(value ="SELECT * FROM transactions where transaction_type = 'TAKEN_LOAN' AND id = :id ", nativeQuery = true)
    Optional<TransactionSnapshot> findTransactionOfTakenLoanType(@Param(value = "id") int id);
    @Query(value ="SELECT * FROM transactions where transaction_type = 'GIVEN_LOAN' AND id = :id ", nativeQuery = true)
    Optional<TransactionSnapshot> findTransactionOfGivenLoanType(@Param(value = "id") int id);
    @Query(value ="SELECT * FROM transactions where transaction_type = 'INNER_TRANSFER' AND id = :id ", nativeQuery = true)
    Optional<TransactionSnapshot> findTransactionOfInnerTransferType(@Param(value = "id") int id);
    TransactionSnapshot save(TransactionSnapshot transaction);
    boolean deleteTransactionById(Integer id);

}

@org.springframework.stereotype.Repository
class TransactionRepositoryImpl implements TransactionRepository{

    final SqlTransactionRepository repository;

    TransactionRepositoryImpl(SqlTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsTransactionById(int id) {
        return repository.existsTransactionById(id);
    }

    @Override
    public Optional<Transaction> findTransactionById(int id) {
        return repository.findTransactionById(id).map(Transaction::restore);
    }

    @Override
    public Optional<Transaction> findTransactionOfIncomeType(int id) {
        return repository.findTransactionOfIncomeType(id).map(Transaction::restore);
    }

    @Override
    public Optional<Transaction> findTransactionOfOutcomeType(int id) {
        return repository.findTransactionOfOutcomeType(id).map(Transaction::restore);
    }

    @Override
    public Optional<Transaction> findTransactionOfReceiptType(int id) {
        return repository.findTransactionOfReceiptType(id).map(Transaction::restore);
    }

    @Override
    public Optional<Transaction> findTransactionOfTakenLoanType(int id) {
        return repository.findTransactionOfTakenLoanType(id).map(Transaction::restore);
    }

    @Override
    public Optional<Transaction> findTransactionOfGivenLoanType(int id) {
        return repository.findTransactionOfGivenLoanType(id).map(Transaction::restore);
    }

    @Override
    public Optional<Transaction> findTransactionOfInnerTransferType(int id) {
        return repository.findTransactionOfInnerTransferType(id).map(Transaction::restore);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return Transaction.restore(repository.save(transaction.getSnapshot()));
    }

    @Override
    public boolean deleteTransactionById(Integer id) {
        return repository.deleteTransactionById(id);
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(int id) {
        return repository.findTransactionsByAccountId(id).stream().map(Transaction::restore).toList();
    }
}
