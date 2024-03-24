import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from '../app-routing.module';
import { ReceiptFormComponent } from './receipt-form/receipt-form.component';
import { ReceiptListComponent } from './receipt-list/receipt-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import {
  FontAwesomeModule,
  FaIconLibrary,
} from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { SearchModule } from '../search/search.module';
import { TransactionService } from '../transaction/service/transaction.service';

@NgModule({
  declarations: [ReceiptFormComponent, ReceiptListComponent],
  imports: [
    BrowserModule,
    SearchModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FontAwesomeModule,
  ],
  providers: [TransactionService],
})
export class ReceiptModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
