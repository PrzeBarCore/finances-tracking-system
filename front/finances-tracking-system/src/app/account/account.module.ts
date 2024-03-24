import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountComponent } from './account.component';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountFormComponent } from './account-form/account-form.component';
import { AppRoutingModule } from '../app-routing.module';
import { SearchModule } from '../search/search.module';
import { TransactionService } from '../transaction/service/transaction.service';

@NgModule({
  declarations: [AccountComponent, AccountListComponent, AccountFormComponent],
  imports: [CommonModule, AppRoutingModule, SearchModule],
  providers: [TransactionService],
})
export class AccountModule {}
