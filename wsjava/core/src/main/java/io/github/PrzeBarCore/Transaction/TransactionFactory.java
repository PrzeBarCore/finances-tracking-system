package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountDto;
import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Account.SimpleAccountDto;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Receipt.ReceiptFacade;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

public class TransactionFactory {

    private final AccountFacade accountFacade;
    private final ReceiptFacade receiptFacade;
    private final CategoryFacade categoryFacade;


    TransactionFactory(AccountFacade accountFacade, ReceiptFacade receiptFacade, CategoryFacade categoryFacade) {
        this.accountFacade = accountFacade;
        this.receiptFacade = receiptFacade;
        this.categoryFacade = categoryFacade;
    }


    Transaction createEntity(SimpleTransactionDto simpleTransactionDto) {
        if(simpleTransactionDto instanceof ReceiptTypeSimpleTransactionDto){
            return Transaction.restore(snapshotFromDto((ReceiptTypeSimpleTransactionDto) simpleTransactionDto));
        }
        if(simpleTransactionDto instanceof InnerTypeSimpleTransactionDto){
            return Transaction.restore(snapshotFromDto((InnerTypeSimpleTransactionDto) simpleTransactionDto));
        }
        if(simpleTransactionDto instanceof LoanTypeSimpleTransaction){
            return Transaction.restore(snapshotFromDto((LoanTypeSimpleTransaction) simpleTransactionDto));
        }
        if(simpleTransactionDto instanceof OutcomeTypeSimpleTransaction){
            return Transaction.restore(snapshotFromDto((OutcomeTypeSimpleTransaction) simpleTransactionDto));
        }
        else {
            return Transaction.restore(snapshotFromDto((IncomeTypeSimpleTransaction) simpleTransactionDto));
        }
    }

    SimpleTransactionDto createDto(Transaction transaction) {
        return dtoFromSnapshot(transaction.getSnapshot());
    }

    SimpleTransactionDto createSimpleDto(Transaction transaction) {
        TransactionSnapshot snapshot = transaction.getSnapshot();
        if(null !=snapshot) {
            TransactionType transactionType = snapshot.getTransactionType();
            return Builder.instance().withId(snapshot.getId())
                    .withIssuedOnDateTime(snapshot.getIssuedOnDateTime())
                    .withTotalValue(snapshot.getTotalValue())
                    .withTransactionType(snapshot.getTransactionType())
                    .buildSimpleTransaction();
        } else {
            return null;
        }
    }

     private SimpleTransactionDto dtoFromSnapshot(TransactionSnapshot transactionSnapshot) {
        if(null !=transactionSnapshot){
            TransactionType transactionType = transactionSnapshot.getTransactionType();
            Builder builderInstance = Builder.instance().withId(transactionSnapshot.getId())
                   .withIssuedOnDateTime(transactionSnapshot.getIssuedOnDateTime())
                   .withTotalValue(transactionSnapshot.getTotalValue());

            if(transactionType.equals(TransactionType.RECEIPT)) {
                return builderInstance.withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                        .withReceiptId(transactionSnapshot.getReceiptId())
                        .buildReceiptTypeTransaction();
            } else {
                builderInstance.withDescription(transactionSnapshot.getDescription());
                if(transactionType.equals(TransactionType.INCOME) || transactionType.equals(TransactionType.OUTCOME)){
                    builderInstance.withTransactionCategory(categoryFacade.findCategoryById(transactionSnapshot.getTransactionCategoryId()).orElseThrow());
                    if(transactionType.equals(TransactionType.INCOME))
                        return builderInstance.withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()).orElseThrow())
                                .buildIncomeTypeTransaction();
                    else
                        return builderInstance.withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                                .buildOutcomeTypeTransaction();
                } else if(transactionType.equals(TransactionType.GIVEN_LOAN) || transactionType.equals(TransactionType.TAKEN_LOAN)){
                    builderInstance.withRepaymentDay(transactionSnapshot.getRepaymentDate());
                    if(transactionType.equals(TransactionType.GIVEN_LOAN))
                        return builderInstance.withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                                .buildOutcomeTypeTransaction();
                    else
                        return builderInstance.withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()).orElseThrow())
                                .buildIncomeTypeTransaction();
                } else {
                    return builderInstance.withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()).orElseThrow())
                            .withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                            .withDescription(transactionSnapshot.getDescription())
                            .buildInnerTypeTransaction();
                }
            }
        }
        else
            return null;
    }

    private TransactionSnapshot snapshotFromDto(ReceiptTypeSimpleTransactionDto transactionDto){
        if(null!=transactionDto)
        return new TransactionSnapshot(transactionDto.getId(),
                transactionDto.getIssuedOnDateTime(),
                transactionDto.getTotalValue(),
                transactionDto.getTransactionType(),
                Description.of(""),
                null,
                null,
                null,
                transactionDto.getSourceAccount().getId(),
                transactionDto.getReceiptId());
        else
            return null;
    }

    private TransactionSnapshot snapshotFromDto(InnerTypeSimpleTransactionDto transactionDto){
        if(null!=transactionDto)
            return new TransactionSnapshot(transactionDto.getId(),
                    transactionDto.getIssuedOnDateTime(),
                    transactionDto.getTotalValue(),
                    transactionDto.getTransactionType(),
                    transactionDto.getDescription(),
                    null,
                    null,
                    transactionDto.getTargetAccount().getId(),
                    transactionDto.getSourceAccount().getId(),
                    null);
        else
            return null;
    }private TransactionSnapshot snapshotFromDto(LoanTypeSimpleTransaction transactionDto){
        if(null!=transactionDto)
            return new TransactionSnapshot(transactionDto.getId(),
                    transactionDto.getIssuedOnDateTime(),
                    transactionDto.getTotalValue(),
                    transactionDto.getTransactionType(),
                    transactionDto.getDescription(),
                    transactionDto.getRepaymentDate(),
                    null,
                    transactionDto.getTransactionType().equals(TransactionType.TAKEN_LOAN.toString()) ? transactionDto.getTargetAccount().map(SimpleAccountDto::getId).orElseThrow() : null,
                    transactionDto.getTransactionType().equals(TransactionType.GIVEN_LOAN.toString()) ? transactionDto.getSourceAccount().map(SimpleAccountDto::getId).orElseThrow() : null,
                    null);
        else
            return null;
    }
    private TransactionSnapshot snapshotFromDto(IncomeTypeSimpleTransaction transactionDto){
        if(null!=transactionDto)
            return new TransactionSnapshot(transactionDto.getId(),
                    transactionDto.getIssuedOnDateTime(),
                    transactionDto.getTotalValue(),
                    transactionDto.getTransactionType(),
                    transactionDto.getDescription(),
                    null,
                    transactionDto.getTransactionCategory().getId(),
                    transactionDto.getTargetAccount().getId(),
                    null,
                    null);
        else
            return null;
    }private TransactionSnapshot snapshotFromDto(OutcomeTypeSimpleTransaction transactionDto){
        if(null!=transactionDto)
            return new TransactionSnapshot(transactionDto.getId(),
                    transactionDto.getIssuedOnDateTime(),
                    transactionDto.getTotalValue(),
                    transactionDto.getTransactionType(),
                    transactionDto.getDescription(),
                    null,
                    transactionDto.getTransactionCategory().getId(),
                    null,
                    transactionDto.getSourceAccount().getId(),
                    null);
        else
            return null;
    }

    private static class Builder{
        private Integer id;
        private LocalDateTime issuedOnDateTime;
        private MonetaryAmount totalValue;
        private TransactionType transactionType;
        private CategoryDto transactionCategory;
        private Description description;
        private LocalDateTime repaymentDate;
        private SimpleAccountDto targetAccount;
        private SimpleAccountDto sourceAccount;
        private Integer receiptId;

        private Builder(){}
        static Builder  instance(){
            return new Builder();
        }

        Builder withId(Integer id){
            this.id=id;
            return this;
        }
        Builder withIssuedOnDateTime(LocalDateTime issuedOnDateTime){
            this.issuedOnDateTime=issuedOnDateTime;
            return this;
        }

        Builder withTotalValue(MonetaryAmount totalValue){
            this.totalValue=totalValue;
            return this;
        }
        Builder withTransactionType(TransactionType transactionType){
            this.transactionType=transactionType;
            return this;
        }
        Builder withTransactionCategory(CategoryDto transactionCategory){
            this.transactionCategory=transactionCategory;
            return this;
        }
        Builder withDescription(Description description){
            this.description=description;
            return this;
        }
        Builder withRepaymentDay(LocalDateTime repaymentDate){
            this.repaymentDate=repaymentDate;
            return this;
        }
        Builder withTargetAccount(SimpleAccountDto targetAccount){
            this.targetAccount=targetAccount;
            return this;
        }
        Builder withSourceAccount(SimpleAccountDto sourceAccount){
            this.sourceAccount=sourceAccount;
            return this;
        }
        Builder withReceiptId(Integer receiptId){
            this.receiptId = receiptId;
            return this;
        }

        ReceiptTypeSimpleTransactionDto buildReceiptTypeTransaction(){
            if(validateData(TransactionType.RECEIPT))
                return new ReceiptTypeSimpleTransactionDto(this.id, this.issuedOnDateTime, this.totalValue, this.sourceAccount, this.receiptId);
            else
                throw new IllegalStateException();
        }
        InnerTypeSimpleTransactionDto buildInnerTypeTransaction(){
            if(validateData(TransactionType.INNER_TRANSFER))
                return new InnerTypeSimpleTransactionDto(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.targetAccount, this.sourceAccount);
            else
                throw new IllegalStateException();
        }
        LoanTypeSimpleTransaction buildLoanTypeTransaction(){
            if(validateData(TransactionType.TAKEN_LOAN) || validateData(TransactionType.GIVEN_LOAN))
                return new LoanTypeSimpleTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.repaymentDate,this.targetAccount, this.sourceAccount);
            else
                throw new IllegalStateException();
        }
        IncomeTypeSimpleTransaction buildIncomeTypeTransaction(){
            if(validateData(TransactionType.INCOME))
                return new IncomeTypeSimpleTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.transactionCategory, this.targetAccount);
            else
                throw new IllegalStateException();
        }
        OutcomeTypeSimpleTransaction buildOutcomeTypeTransaction(){
            if(validateData(TransactionType.OUTCOME))
                return new OutcomeTypeSimpleTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.transactionCategory, this.sourceAccount);
            else
                throw new IllegalStateException();
        }

        SimpleTransactionDto buildSimpleTransaction(){
            if(validateData())
                return new SimpleTransactionDto(this.id, this.issuedOnDateTime, this.totalValue, this.transactionType);
            else
                throw new IllegalStateException();
        }

        private boolean validateData(TransactionType transactionType){
            boolean result = validateData();
            if(transactionType.equals(TransactionType.RECEIPT)){
                result &= this.sourceAccount != null && this.receiptId != null;
            } else if(transactionType.equals(TransactionType.INNER_TRANSFER)){
                result &= this.sourceAccount != null && this.targetAccount != null && this.description != null;
            } else if(transactionType.equals(TransactionType.TAKEN_LOAN)){
                result &= this.targetAccount != null && this.repaymentDate != null && this.description != null;
            } else if(transactionType.equals(TransactionType.GIVEN_LOAN)){
                result &= this.sourceAccount != null && this.repaymentDate != null && this.description != null;
            } else if(transactionType.equals(TransactionType.INCOME)){
                result &= this.targetAccount != null && this.transactionCategory != null && this.description != null;
            } else if(transactionType.equals(TransactionType.OUTCOME)){
                result &= this.sourceAccount != null && this.transactionCategory != null && this.description != null;
            }
            return result;
        }

        private boolean validateData(){
            if(this.id != null & this.issuedOnDateTime != null && this.totalValue !=null)
                return true;
            else
                return false;
        }
    }
}
