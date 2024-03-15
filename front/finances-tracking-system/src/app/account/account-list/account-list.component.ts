import { Component } from '@angular/core';
import { Account } from '../model/account';
import { AccountService } from '../service/account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.scss'],
})
export class AccountListComponent {
  accountList: Account[] = [];
  constructor(private accountService: AccountService, private router: Router) {
    accountService
      .getAccounts()
      .subscribe((response) => (this.accountList = response));
  }

  openAccount(accountId: number) {
    this.router.navigate(['/accounts/details', accountId]);
  }

  openAccountForm() {
    this.router.navigate(['/accounts', -1]);
  }
}
