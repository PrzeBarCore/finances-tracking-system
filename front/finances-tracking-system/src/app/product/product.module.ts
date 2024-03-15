import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductComponent } from './product.component';
import { CategoryModule } from '../category/category.module';
import {
  FontAwesomeModule,
  FaIconLibrary,
} from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { ProductFormComponent } from './product-form/product-form.component';
import { SearchModule } from '../search/search.module';
@NgModule({
  declarations: [ProductComponent, ProductFormComponent],
  imports: [
    CommonModule,
    CategoryModule,
    SearchModule,
    FontAwesomeModule,
    FormsModule,
  ],
})
export class ProductModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
