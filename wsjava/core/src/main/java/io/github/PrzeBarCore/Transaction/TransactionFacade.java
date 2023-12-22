package io.github.PrzeBarCore.Transaction;

public class TransactionFacade {
    private final TransactionRepository transactionRepository;
    private final TransactionFactory transactionFactory;

    TransactionFacade(TransactionRepository transactionRepository, TransactionFactory transactionFactory) {
        this.transactionRepository = transactionRepository;
        this.transactionFactory = transactionFactory;
    }

    public TransactionDto addTransaction(final TransactionDto transactionDto) {
        if(!transactionRepository.existsTransactionById(transactionDto.getId())){
            return transactionFactory.createDto(transactionRepository.save(transactionFactory.createEntity(transactionDto)));
        }
        else
            return transactionDto;
    }
}
