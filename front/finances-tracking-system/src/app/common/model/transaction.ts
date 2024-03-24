import { Account } from 'src/app/account/model/account';
import { Category } from 'src/app/category/model/category';
import { Receipt } from 'src/app/receipt/model/receipt';

export class Transaction {
  public id: number = 0;
  public issuedOnDateTime: Date = new Date();
  public totalValue: number = 0;
  public transactionType: string = '';
  public description: string = '';
  public repaymentDate: Date = new Date();
  public transactionCategory: Category | null = null;
  public targetAccount: Account | null = null;
  public sourceAccount: Account | null = null;
  public receiptId: number = 0;
  constructor(
    id: number,
    issuedOnDateTime: Date,
    totalValue: number,
    transactionType: string,
    description: string,
    repaymentDate: Date,
    transactionCategory: Category | null,
    targetAccount: Account | null,
    sourceAccount: Account | null,
    receiptId: number
  ) {
    this.id = id;
    this.issuedOnDateTime = issuedOnDateTime;
    this.totalValue = totalValue;
    this.transactionType = transactionType;
    this.description = description;
    this.repaymentDate = repaymentDate;
    this.transactionCategory = transactionCategory;
    this.targetAccount = targetAccount;
    this.sourceAccount = sourceAccount;
    this.receiptId = receiptId;
  }

  public static getNormalizedTransactionType(transactionType: string): string {
    return (
      transactionType.substring(0, 1).toLocaleUpperCase().toString() +
      transactionType.substring(1).toLowerCase().replaceAll('_', ' ').toString()
    );
  }
}
