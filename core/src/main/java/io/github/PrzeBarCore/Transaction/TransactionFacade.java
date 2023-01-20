package io.github.PrzeBarCore.Transaction;

public class TransactionFacade {
    TransactionRepository transactionRepository;

    TransactionFacade(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
