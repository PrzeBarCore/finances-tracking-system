import { Transaction } from 'src/app/common/model/transaction';

export class Account {
  public id: number = 0;
  public name: string = '';
  public balance: number = 0;
  public currency: string = '';
  public transactionList: Transaction[] = [];
  constructor(
    id: number,
    name: string,
    balance: number,
    currency: string,
    transactions: Transaction[]
  ) {
    this.id = id;
    this.name = name;
    this.balance = balance;
    this.currency = currency;
    this.transactionList = transactions;
  }
}

export class SimpleAccount {
  public id: number = 0;
  public name: string = '';
  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}
