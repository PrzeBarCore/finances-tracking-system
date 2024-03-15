import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryComponent } from './category.component';
import { FormsModule } from '@angular/forms';
import { CategoryDataService } from './services/category-data.service';
import { MatTreeModule } from '@angular/material/tree';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  FontAwesomeModule,
  FaIconLibrary,
} from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { CategoryFormComponent } from './category-form/category-form.component';
import { AppRoutingModule } from '../app-routing.module';
import { CategoryTreeComponent } from './category-tree/category-tree.component';

@NgModule({
  declarations: [
    CategoryComponent,
    CategoryFormComponent,
    CategoryTreeComponent,
  ],
  imports: [
    CommonModule,
    MatTreeModule,
    MatButtonModule,
    MatIconModule,
    FontAwesomeModule,
    FormsModule,
    AppRoutingModule,
  ],
  exports: [CategoryTreeComponent],
  providers: [CategoryDataService],
})
export class CategoryModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
