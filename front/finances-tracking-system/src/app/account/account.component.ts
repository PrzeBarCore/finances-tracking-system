import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  OnInit,
} from '@angular/core';
import { AccountService } from './service/account.service';
import { Account } from './model/account';
import { ActivatedRoute, Router } from '@angular/router';
import { Transaction } from '../common/model/transaction';

@Component({
  selector: 'account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss'],
})
export class AccountComponent {
  accountInstance: Account = new Account(0, '', 0, '', []);

  constructor(
    private accountService: AccountService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.accountService
      .getAccountDetails(this.activatedRoute.snapshot.params['id'])
      .subscribe((response) => {
        this.accountInstance = response;
      });
  }

  addNewTransaction(transactionType: string) {
    if (transactionType == 'RECEIPT')
      this.router.navigate(['/receipts/-1', { aid: this.accountInstance.id }]);
  }

  normalizeTransactionType(type : string){
    return Transaction.getNormalizedTransactionType(type);
  }
}
