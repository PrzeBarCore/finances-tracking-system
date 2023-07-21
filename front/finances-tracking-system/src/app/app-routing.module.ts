import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReceiptListComponent } from './receipt/receipt-list/receipt-list.component';
import { ReceiptFormComponent } from './receipt/receipt-form/receipt-form.component';

const routes: Routes = [
  { path: 'receipts', component: ReceiptListComponent },
  { path: 'addreceipt', component: ReceiptFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
