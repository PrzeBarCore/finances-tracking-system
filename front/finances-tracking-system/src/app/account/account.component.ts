import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  OnInit,
  ViewChild,
} from '@angular/core';
import { AccountService } from './service/account.service';
import { Account } from './model/account';
import { ActivatedRoute, Router } from '@angular/router';
import { Transaction } from '../common/model/transaction';
import { DialogComponent, DialogType } from '../search/dialog.component';
import { TransactionService } from '../transaction/service/transaction.service';
import { ComponentType, GlobalRoutes } from '../common/globals/globalRoutes';

@Component({
  selector: 'account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss'],
})
export class AccountComponent implements AfterViewInit {
  accountInstance: Account = new Account(0, '', 0, '', []);
  @ViewChild('warnningDialog', { static: true })
  dialog!: DialogComponent;
  dialogType: DialogType = DialogType.WARRNING;

  constructor(
    private accountService: AccountService,
    private transactionService: TransactionService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.accountService
      .getAccountDetails(this.activatedRoute.snapshot.params['id'])
      .subscribe((response) => {
        this.accountInstance = response;
      });
  }
  ngAfterViewInit(): void {
    this.dialog.close();
  }

  addNewTransaction(transactionType: string) {
    if (transactionType == 'RECEIPT') {
      console.log(GlobalRoutes.RECEIPT_FORM);
      this.router.navigate([
        GlobalRoutes.getUrlForComponent(ComponentType.RECEIPT_FORM, ['-1']),
        { aid: this.accountInstance.id },
      ]);
    }
  }

  openDialog(transaction: Transaction) {
    this.dialog.open();
    this.dialog.confirmButtonClicked.subscribe((result) => {
      if (result)
        this.transactionService.deleteTransaction(transaction.id).subscribe();
    });
  }
  deleteTransaction() {}

  normalizeTransactionType(type: string) {
    return Transaction.getNormalizedTransactionType(type);
  }

  navigateToTransaction(transaction: Transaction) {
    if (transaction.transactionType == 'RECEIPT') {
      console.log();
      this.router.navigate([
        GlobalRoutes.getUrlForComponent(ComponentType.RECEIPT_FORM, [
          transaction.receiptId.toString(),
        ]),
      ]);
    } else
      this.router.navigate([
        GlobalRoutes.getUrlForComponent(ComponentType.TRANSACTION_FORM, [
          transaction.id.toString(),
        ]),
      ]);
  }
}
