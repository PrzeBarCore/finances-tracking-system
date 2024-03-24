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
import { TransactionComponent } from './transaction/transaction.component';
import { GlobalRoutes } from './common/globals/globalRoutes';

const routes: Routes = [
  { path: GlobalRoutes.ACCOUNT_LIST, component: AccountListComponent },
  { path: GlobalRoutes.ACCOUNT_FORM, component: AccountFormComponent },
  { path: GlobalRoutes.ACCOUNT_DETAILS, component: AccountComponent },
  { path: 'transactions/receipts', component: ReceiptListComponent },
  { path: GlobalRoutes.RECEIPT_FORM, component: ReceiptFormComponent },
  { path: 'categories', component: CategoryComponent },
  { path: GlobalRoutes.CATEGORY_LIST, component: CategoryComponent },
  { path: GlobalRoutes.CATEGORY_FORM, component: CategoryFormComponent },
  { path: GlobalRoutes.PRODUCT_LIST, component: ProductComponent },
  { path: GlobalRoutes.PRODUCT_FORM, component: ProductFormComponent },
  { path: GlobalRoutes.TRANSACTION_FORM, component: TransactionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
