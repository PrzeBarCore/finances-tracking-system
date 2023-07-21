import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from '../app-routing.module';
import { ReceiptFormComponent } from './receipt-form/receipt-form.component';
import { ReceiptListComponent } from './receipt-list/receipt-list.component';
import { ReceiptService } from './service/receipt-service';

@NgModule({
  declarations: [ReceiptFormComponent, ReceiptListComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    AppRoutingModule,
  ],
  providers: [ReceiptService],
})
export class ReceiptModule {}
