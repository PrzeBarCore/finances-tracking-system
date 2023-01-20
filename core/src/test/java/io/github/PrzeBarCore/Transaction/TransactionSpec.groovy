package io.github.PrzeBarCore.Transaction

import io.github.PrzeBarCore.Transaction.Dto.TransactionCategoryDto
import io.github.PrzeBarCore.Transaction.Dto.TransactionDto
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount
import io.github.PrzeBarCore.ValueObjects.TransactionType

import java.time.LocalDateTime

class TransactionSpec {

    TransactionFacade facade=new TransactionFacade(new InMemoryTransactionRepository());
    private int exampleAccountId=1;
    private var parentCategory=createCategory(1,"School", 0, null);
    private var childCategory=createCategory(2,"Pencils", 1, parentCategory);

    private var firstTransaction=createTransaction(1,exampleAccountId,LocalDateTime.now().minusWeeks(1),MonetaryAmount.of(BigDecimal.valueOf(200)), childCategory,TransactionType.OUTCOME);
    private var secondTransaction=createTransaction(2,exampleAccountId,LocalDateTime.now(),MonetaryAmount.of(BigDecimal.valueOf(100)), childCategory,TransactionType.OUTCOME);
    private var thirdTransaction=createTransaction(1,exampleAccountId+1,LocalDateTime.now().minusWeeks(1),MonetaryAmount.of(BigDecimal.valueOf(200)), childCategory,TransactionType.OUTCOME);


    def "should get a transaction"() {
        when: "new transaction is added"
        facade.addTransaction(firstTransaction)

        then: "system has this transaction"
        facade.find(firstTransaction.getId())==firstTransaction
    }

    def "should list transaction for specific account id"(){
        given: "multiple transaction with different account id exists"
        facade.addTransaction(firstTransaction)
        facade.addTransaction(secondTransaction)
        facade.addTransaction(thirdTransaction)

        when: "we ask for transactions for specific account id"
        List<TransactionDto> result=facade.findTransactionsByAccountId(exampleAccountId)

        then: "we get list containing added "
        result.contains(firstTransaction)
        result.contains(secondTransaction)
        !result.contains(thirdTransaction)
    }

    private TransactionCategoryDto createCategory(int id, String name, int dependencyLevel, TransactionCategoryDto parentCategory){
        return TransactionCategoryDto.builder().withId(id)
                .withName(name)
                .withDependencyLevel(dependencyLevel)
                .withParentCategory(parentCategory)
                .build();
    }

    private TransactionDto createTransaction(int id, int accountId, LocalDateTime date,  MonetaryAmount value, TransactionCategoryDto category, TransactionType type){
        return TransactionDto.builder().withId(id)
        .withAccountId(accountId)
        .withIssuedOnDateTime(date)
        .withTotalValue(value)
        .withTransactionCategory(category)
        .withTransactionType(type)
        .build();
    }
}
