import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DialogComponent } from './dialog.component';
import { CategoryModule } from '../category/category.module';
import { CategorySearchDialogComponent } from './category-search-dialog/category-search-dialog.component';
import { DataGridComponent } from '../common/data-grid/data-grid.component';
import { TransactionTypeSearchDialogComponent } from './transaction-type-search-dialog/transaction-type-search-dialog.component';
import { ProductSearchDialogComponent } from './product-search-dialog/product-search-dialog.component';

@NgModule({
  declarations: [
    DialogComponent,
    CategorySearchDialogComponent,
    TransactionTypeSearchDialogComponent,
    ProductSearchDialogComponent,
  ],
  imports: [CommonModule, CategoryModule, DataGridComponent],
  providers: [],
  exports: [
    CategorySearchDialogComponent,
    TransactionTypeSearchDialogComponent,
    ProductSearchDialogComponent,
    DialogComponent,
  ],
})
export class SearchModule {}
