import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReceiptListComponent } from './receipt/receipt-list/receipt-list.component';
import { ReceiptFormComponent } from './receipt/receipt-form/receipt-form.component';
import { CategoryComponent } from './category/category.component';
import { CategoryFormComponent } from './category/category-form/category-form.component';
import { ProductComponent } from './product/product.component';
import { ProductFormComponent } from './product/product-form/product-form.component';
import { AccountListComponent } from './account/account-list/account-list.component';
import { AccountComponent } from './account/account.component';
import { AccountFormComponent } from './account/account-form/account-form.component';

const routes: Routes = [
  { path: 'accounts', component: AccountListComponent },
  { path: 'accounts/:id', component: AccountFormComponent },
  { path: 'accounts/details/:id', component: AccountComponent },
  { path: 'receipts', component: ReceiptListComponent },
  { path: 'receipts/:id', component: ReceiptFormComponent },
  { path: 'categories', component: CategoryComponent },
  { path: 'categories/:type', component: CategoryComponent },
  { path: 'categories/:type/:id', component: CategoryFormComponent },
  { path: 'products', component: ProductComponent },
  { path: 'products/:id', component: ProductFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
