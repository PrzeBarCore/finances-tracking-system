import { ReceiptItem } from './receipt-item';

export class Receipt {
  issuedOnDate: Date;
  totalValue: number;
  isContainingListOfItems: boolean;
  listOfItems: ReceiptItem[];

  constructor(
    issuedOnDate: Date,
    totalValue: number,
    isContainingListOfItems: boolean,
    listOfItems: ReceiptItem[]
  ) {
    this.issuedOnDate = issuedOnDate;
    this.totalValue = totalValue;
    this.isContainingListOfItems = isContainingListOfItems;
    this.listOfItems = listOfItems;
  }
}
