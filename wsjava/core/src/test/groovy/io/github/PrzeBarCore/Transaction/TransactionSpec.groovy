package io.github.PrzeBarCore.Transaction


import spock.lang.Specification

class TransactionSpec extends Specification {
//
//    def dt= LocalDateTime.now()
//    def facade=new TransactionFacade(new InMemoryTransactionRepository());
//    int exampleAccountId=1;
//    def parentCategory=createCategory(1,"School", 0, null);
//    def childCategory=createCategory(2,"Pencils", 1, parentCategory);
//
//    def firstTransaction=createTransaction(1,exampleAccountId,dt,MonetaryAmount.of(200.0g), childCategory,TransactionType.OUTCOME);
//    def secondTransaction=createTransaction(2,exampleAccountId, dt,MonetaryAmount.of(100.0g), childCategory,TransactionType.OUTCOME);
//    def thirdTransaction=createTransaction(3 ,exampleAccountId+1,dt,MonetaryAmount.of(200.0g), childCategory,TransactionType.OUTCOME);
//
//
//    def "should get a transaction"() {
//        when: "new transaction is added"
//        facade.addTransaction(firstTransaction)
//
//        then: "system has this transaction"
//        facade.find(firstTransaction.getId())==firstTransaction
//    }
//
//    def "should list transaction for specific account id"(){
//        given: "multiple transaction with different account id exists"
//        facade.addTransaction(firstTransaction)
//        facade.addTransaction(secondTransaction)
//        facade.addTransaction(thirdTransaction)
//
//        when: "we ask for transactions for specific account id"
//        List<TransactionDto> result=facade.findTransactionsByAccountId(exampleAccountId)
//
//        then: "we get list containing added "
//        result.contains(firstTransaction)
//        result.contains(secondTransaction)
//        !result.contains(thirdTransaction)
//    }
//
//    private TransactionCategoryDto createCategory(int id, String name, int dependencyLevel, TransactionCategoryDto parentCategory){
//        return TransactionCategoryDto.builder().withId(id)
//                .withName(NameString.of(name))
//                .withDependencyLevel(dependencyLevel)
//                .withParentCategory(parentCategory)
//                .build();
//    }
//
//    private TransactionDto createTransaction(int id, int accountId, LocalDateTime date,  MonetaryAmount value, TransactionCategoryDto category, TransactionType type){
//        return TransactionDto.builder().withId(id)
//        .withAccountId(accountId)
//        .withIssuedOnDateTime(date)
//        .withTotalValue(value)
//        .withTransactionCategory(category)
//        .withTransactionType(type)
//        .build();
//    }
}
