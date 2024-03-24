package io.github.PrzeBarCore.Receipt;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

interface SqlReceiptRepository extends Repository<ReceiptSnapshot, Integer> {
    ReceiptSnapshot save(ReceiptSnapshot receipt);
    Optional<ReceiptSnapshot> findById(int id);
    Optional<ReceiptSnapshot> findByRelatedTransactionId(int id);
    boolean existsReceiptById(Integer receiptId);
    boolean deleteReceiptById(Integer receiptId);
}

@org.springframework.stereotype.Repository
class ReceiptRepositoryImpl implements ReceiptRepository {
    final SqlReceiptRepository repository;

    ReceiptRepositoryImpl(SqlReceiptRepository repository) {
        this.repository = repository;
    }

    @Override
    public Receipt save(Receipt receipt) {
        return Receipt.restore(repository.save(receipt.getSnapshot()));
    }

    @Override
    public Optional<Receipt> findById(int id) {
        return repository.findById(id).map(Receipt::restore);
    }

    @Override
    public Optional<Receipt> findByRelatedTransactionId(int id) { return repository.findByRelatedTransactionId(id).map(Receipt::restore); }

    @Override
    public boolean existsReceiptById(Integer receiptId) {
        return repository.existsReceiptById(receiptId);
    }

    @Override
    public boolean deleteReceiptById(Integer receiptId) {
        return repository.deleteReceiptById(receiptId);
    }
}
