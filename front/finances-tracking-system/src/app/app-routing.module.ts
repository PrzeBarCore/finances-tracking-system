import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReceiptListComponent } from './receipt/receipt-list/receipt-list.component';
import { ReceiptFormComponent } from './receipt/receipt-form/receipt-form.component';
import { CategoryComponent } from './category/category.component';
import { TestComponent } from './test/test.component';
import { CategoryFormComponent } from './category/category-form/category-form.component';

const routes: Routes = [
  { path: 'receipts', component: ReceiptListComponent },
  { path: 'addreceipt', component: ReceiptFormComponent },
  { path: 'categories', component: CategoryComponent },
  { path: 'categories/:type', component: CategoryComponent },
  { path: 'categories/:type/:id', component: CategoryFormComponent },
  { path: 'test', component: TestComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
