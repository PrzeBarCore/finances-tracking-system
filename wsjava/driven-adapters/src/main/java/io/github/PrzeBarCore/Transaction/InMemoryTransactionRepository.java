package io.github.PrzeBarCore.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//class InMemoryTransactionRepository implements TransactionRepository {
//
//    private ConcurrentHashMap<Integer, Transaction> map= new ConcurrentHashMap<>();
//    InMemoryTransactionRepository() {
//    }
//
//    @Override
//    public boolean existsTransactionById(int id) {
//        return map.containsKey(id);
//    }
//
//    @Override
//    public Optional<Transaction> findTransactionOfIncomeType(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Transaction> findTransactionOfOutcomeType(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Transaction> findTransactionOfReceiptType(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Transaction> findTransactionOfTakenLoanType(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Transaction> findTransactionOfGivenLoanType(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Transaction> findTransactionOfInnerTransferType(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Transaction> find(int id) {
//        return Optional.ofNullable(map.get(id));
//    }
//
//    @Override
//    public Transaction saveTransaction(Transaction transaction) {
//        if(null!=transaction){
//            Integer id=transaction.getSnapshot().getId();
//            if(map.containsKey(id)){
//                map.remove(id);
//            }
//            map.put(id,transaction);
//        }
//        return transaction;
//    }
//
//    @Override
//    public List<Transaction> findTransactionsByAccountIdOrderByIssuedOnDateTime(int id) {
//       return map.values().stream()
//                .filter(c -> c.getSnapshot().getAccountId()==id)
//                .sorted(byIssuedOnDateTime).collect(Collectors.toList());
//    }
//
//    private final Comparator<Transaction> byIssuedOnDateTime= (o1, o2) -> {
//        var issuedOnDateTimeO1=o1.getSnapshot().getIssuedOnDateTime();
//        var issuedOnDateTimeO2=o2.getSnapshot().getIssuedOnDateTime();
//        if(issuedOnDateTimeO1.isAfter(issuedOnDateTimeO2))
//            return 1;
//        else if(issuedOnDateTimeO1.isEqual(issuedOnDateTimeO2))
//            return 0;
//        return -1;
//    };
//
//}
