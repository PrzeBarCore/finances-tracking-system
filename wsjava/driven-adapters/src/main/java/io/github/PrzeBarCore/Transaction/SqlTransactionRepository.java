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
    Optional<TransactionSnapshot> findTransactionByReceiptId(int id);
    @Query(value ="SELECT * FROM transactions where source_account_id = :id OR target_account_id = :id", nativeQuery = true)
    List<TransactionSnapshot> findTransactionsByAccountId(@Param(value = "id") int id);
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
    public Optional<Transaction> findTransactionByReceiptId(int id) {
        return repository.findTransactionByReceiptId(id).map(Transaction::restore);
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
