import { Account, SimpleAccount } from 'src/app/account/model/account';

export class ReceiptTransaction {
  constructor(
    public id: number,
    public issuedOnDateTime: Date,
    public totalValue: number,
    public sourceAccount: SimpleAccount | null,
    public receiptId: number
  ) {}
}
