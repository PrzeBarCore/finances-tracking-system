import { Account, SimpleAccount } from 'src/app/account/model/account';
import { Receipt } from 'src/app/receipt/model/receipt';

export class ReceiptTransaction {
  constructor(
    public id: number,
    public issuedOnDateTime: Date,
    public totalValue: number,
    public sourceAccount: SimpleAccount | null,
    public receipt: Receipt
  ) {}
}
