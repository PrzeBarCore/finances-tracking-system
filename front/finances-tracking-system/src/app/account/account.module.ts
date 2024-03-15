import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountComponent } from './account.component';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountFormComponent } from './account-form/account-form.component';
import { AppRoutingModule } from '../app-routing.module';
import { SearchModule } from '../search/search.module';

@NgModule({
  declarations: [AccountComponent, AccountListComponent, AccountFormComponent],
  imports: [CommonModule, AppRoutingModule, SearchModule],
  providers: [],
})
export class AccountModule {}
