package io.github.PrzeBarCore.Transaction;

import io.github.PrzeBarCore.Account.AccountFacade;
import io.github.PrzeBarCore.Account.SimpleAccountDto;
import io.github.PrzeBarCore.Category.CategoryDto;
import io.github.PrzeBarCore.Category.CategoryFacade;
import io.github.PrzeBarCore.Product.ProductFacade;
import io.github.PrzeBarCore.Product.SimpleProductDto;
import io.github.PrzeBarCore.ValueObjects.Description;
import io.github.PrzeBarCore.ValueObjects.MonetaryAmount;
import io.github.PrzeBarCore.ValueObjects.TransactionType;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.toList;

public class TransactionFactory {
    private final AccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final ProductFacade productFacade;

    TransactionFactory(AccountFacade accountFacade, CategoryFacade categoryFacade,ProductFacade productFacade) {
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
        this.productFacade = productFacade;
    }

    Receipt createReceiptEntity(ReceiptDto dto){
        return Receipt.restore(receiptSnapshotFromDto(dto));
    }

    ReceiptDto createReceiptDto(Receipt receipt){
        return receiptDtoFromSnapshot(receipt.getSnapshot());
    }

    Transaction createTransactionEntity(SimpleTransactionDto simpleTransactionDto) {
        if(simpleTransactionDto instanceof ReceiptTypeTransaction){
            return Transaction.restore(transactionSnapshotFromDto((ReceiptTypeTransaction) simpleTransactionDto));
        }
        if(simpleTransactionDto instanceof InnerTypeTransaction){
            return Transaction.restore(transactionSnapshotFromDto((InnerTypeTransaction) simpleTransactionDto));
        }
        if(simpleTransactionDto instanceof LoanTypeTransaction){
            return Transaction.restore(transactionSnapshotFromDto((LoanTypeTransaction) simpleTransactionDto));
        }
        if(simpleTransactionDto instanceof OutcomeTypeTransaction){
            return Transaction.restore(transactionSnapshotFromDto((OutcomeTypeTransaction) simpleTransactionDto));
        }
        else {
            return Transaction.restore(transactionSnapshotFromDto((IncomeTypeTransaction) simpleTransactionDto));
        }
    }

    SimpleTransactionDto createTransactionDto(Transaction transaction) {
        return transactionDtoFromSnapshot(transaction.getSnapshot());
    }

    private ReceiptSnapshot receiptSnapshotFromDto(ReceiptDto dto){
        return new ReceiptSnapshot(dto.getId(),
                dto.getTotalDiscount(),
                dto.getItems().stream()
                        .map(item -> new ReceiptItemSnapshot(item.getId(),
                                item.getName(),
                                item.getQuantity(),
                                item.getRegularPrice(),
                                item.getDiscount(),
                                item.getProduct().map(SimpleProductDto::getId).orElse(null),
                                item.getExpenseCategory().getId()))
                        .collect(toList()));
    }

    private ReceiptDto receiptDtoFromSnapshot(ReceiptSnapshot snapshot){
        return new ReceiptDto(
                snapshot.getId(),
                snapshot.getTotalDiscount(),
                snapshot.getItems().stream()
                        .map(item -> new ReceiptDto.DtoItem(item.getId(),
                                item.getName(),
                                item.getQuantity(),
                                item.getRegularPrice(),
                                item.getDiscount(),
                                productFacade.findSimpleProduct(item.getProductId()),
                                categoryFacade.findCategoryById(item.getExpenseCategoryId()).orElseThrow(IllegalArgumentException::new)))
                        .collect(toList()));
    }

     private SimpleTransactionDto transactionDtoFromSnapshot(TransactionSnapshot transactionSnapshot) {
        if(null !=transactionSnapshot){
            TransactionType transactionType = transactionSnapshot.getTransactionType();
            TransactionBuilder transactionBuilderInstance = TransactionBuilder.instance().withId(transactionSnapshot.getId())
                   .withIssuedOnDateTime(transactionSnapshot.getIssuedOnDateTime())
                   .withTotalValue(transactionSnapshot.getTotalValue());

            if(transactionType.equals(TransactionType.RECEIPT)) {
                return transactionBuilderInstance.withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                        .withReceipt(receiptDtoFromSnapshot(transactionSnapshot.getReceipt()))
                        .buildReceiptTypeTransaction();
            } else {
                transactionBuilderInstance.withDescription(transactionSnapshot.getDescription());
                if(transactionType.equals(TransactionType.INCOME) || transactionType.equals(TransactionType.OUTCOME)){
                    transactionBuilderInstance.withTransactionCategory(categoryFacade.findCategoryById(transactionSnapshot.getTransactionCategoryId()).orElseThrow());
                    if(transactionType.equals(TransactionType.INCOME))
                        return transactionBuilderInstance.withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()).orElseThrow())
                                .buildIncomeTypeTransaction();
                    else
                        return transactionBuilderInstance.withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                                .buildOutcomeTypeTransaction();
                } else if(transactionType.equals(TransactionType.GIVEN_LOAN) || transactionType.equals(TransactionType.TAKEN_LOAN)){
                    transactionBuilderInstance.withRepaymentDay(transactionSnapshot.getRepaymentDate());
                    if(transactionType.equals(TransactionType.GIVEN_LOAN))
                        return transactionBuilderInstance.withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                                .buildOutcomeTypeTransaction();
                    else
                        return transactionBuilderInstance.withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()).orElseThrow())
                                .buildIncomeTypeTransaction();
                } else {
                    return transactionBuilderInstance.withTargetAccount(accountFacade.findAccount(transactionSnapshot.getTargetAccountId()).orElseThrow())
                            .withSourceAccount(accountFacade.findAccount(transactionSnapshot.getSourceAccountId()).orElseThrow())
                            .withDescription(transactionSnapshot.getDescription())
                            .buildInnerTypeTransaction();
                }
            }
        }
        else
            return null;
    }

    private TransactionSnapshot transactionSnapshotFromDto(ReceiptTypeTransaction transactionDto){
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
                receiptSnapshotFromDto(transactionDto.getReceipt()));
        else
            return null;
    }

    private TransactionSnapshot transactionSnapshotFromDto(InnerTypeTransaction transactionDto){
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
    }
    private TransactionSnapshot transactionSnapshotFromDto(LoanTypeTransaction transactionDto){
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
    private TransactionSnapshot transactionSnapshotFromDto(IncomeTypeTransaction transactionDto){
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
    }
    private TransactionSnapshot transactionSnapshotFromDto(OutcomeTypeTransaction transactionDto){
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

    private static class TransactionBuilder {
        private Integer id;
        private LocalDateTime issuedOnDateTime;
        private MonetaryAmount totalValue;
        private TransactionType transactionType;
        private CategoryDto transactionCategory;
        private Description description;
        private LocalDateTime repaymentDate;
        private SimpleAccountDto targetAccount;
        private SimpleAccountDto sourceAccount;
        private ReceiptDto receipt;

        private TransactionBuilder(){}
        static TransactionBuilder instance(){
            return new TransactionBuilder();
        }

        TransactionBuilder withId(Integer id){
            this.id=id;
            return this;
        }
        TransactionBuilder withIssuedOnDateTime(LocalDateTime issuedOnDateTime){
            this.issuedOnDateTime=issuedOnDateTime;
            return this;
        }

        TransactionBuilder withTotalValue(MonetaryAmount totalValue){
            this.totalValue=totalValue;
            return this;
        }
        TransactionBuilder withTransactionType(TransactionType transactionType){
            this.transactionType=transactionType;
            return this;
        }
        TransactionBuilder withTransactionCategory(CategoryDto transactionCategory){
            this.transactionCategory=transactionCategory;
            return this;
        }
        TransactionBuilder withDescription(Description description){
            this.description=description;
            return this;
        }
        TransactionBuilder withRepaymentDay(LocalDateTime repaymentDate){
            this.repaymentDate=repaymentDate;
            return this;
        }
        TransactionBuilder withTargetAccount(SimpleAccountDto targetAccount){
            this.targetAccount=targetAccount;
            return this;
        }
        TransactionBuilder withSourceAccount(SimpleAccountDto sourceAccount){
            this.sourceAccount=sourceAccount;
            return this;
        }
        TransactionBuilder withReceipt(ReceiptDto receiptId){
            this.receipt = receiptId;
            return this;
        }

        ReceiptTypeTransaction buildReceiptTypeTransaction(){
            if(validateData(TransactionType.RECEIPT))
                return new ReceiptTypeTransaction(this.id, this.issuedOnDateTime, this.totalValue, this.sourceAccount, this.receipt);
            else
                throw new IllegalStateException();
        }
        InnerTypeTransaction buildInnerTypeTransaction(){
            if(validateData(TransactionType.INNER_TRANSFER))
                return new InnerTypeTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.targetAccount, this.sourceAccount);
            else
                throw new IllegalStateException();
        }
        LoanTypeTransaction buildLoanTypeTransaction(){
            if(validateData(TransactionType.TAKEN_LOAN) || validateData(TransactionType.GIVEN_LOAN))
                return new LoanTypeTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.repaymentDate,this.targetAccount, this.sourceAccount);
            else
                throw new IllegalStateException();
        }
        IncomeTypeTransaction buildIncomeTypeTransaction(){
            if(validateData(TransactionType.INCOME))
                return new IncomeTypeTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.transactionCategory, this.targetAccount);
            else
                throw new IllegalStateException();
        }
        OutcomeTypeTransaction buildOutcomeTypeTransaction(){
            if(validateData(TransactionType.OUTCOME))
                return new OutcomeTypeTransaction(this.id, this.issuedOnDateTime, this.totalValue,this.description,this.transactionCategory, this.sourceAccount);
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
                result &= this.sourceAccount != null && this.receipt != null;
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
