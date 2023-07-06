package io.github.PrzeBarCore.Receipt;


import org.springframework.data.repository.Repository;

import java.util.Optional;

interface SqlReceiptRepository extends Repository<ReceiptSnapshot, Integer> {
    ReceiptSnapshot save(ReceiptSnapshot receipt);

    Optional<ReceiptSnapshot> findById(int id);
}

@org.springframework.stereotype.Repository
class ReceiptRepositoryImpl implements ReceiptRepository{
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
}
