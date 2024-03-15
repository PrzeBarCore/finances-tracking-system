import { Component } from '@angular/core';
import { Category, CategoryType } from '../category/model/category';
import { CategoryDataService } from '../category/services/category-data.service';
import { Product } from './model/product';
import { ProductDataService } from './services/product-data.service';
import { CategoryTreeEvent } from '../category/category-tree/category-tree.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent {

  public categories: Category[] = [];
  public checkedCategories: string[] = [];
  public allProducts: Product[] = [];
  public displayedProducts: Product[] = [];

  constructor(
    private categoryService: CategoryDataService,
    private productService: ProductDataService,
    private router : Router
  ) {
    this.categoryService
      .getRootCategoriesOfType(CategoryType.Product)
      .subscribe((response) => {
        this.categories = response;
        this.categories.forEach((category) =>
          this.addCheckedCategoryToPlainList(this.checkedCategories, category)
        );
      });
    this.productService.getProducts().subscribe((response) => {
      this.allProducts = response;
      this.displayedProducts = response;
    });
  }

  receiveMessage(event: CategoryTreeEvent) {
    switch (event.state) {
      case 'selectionChanged':
        this.checkedCategories = [];
        this.displayedProducts = [];
        this.categories.forEach((category) => {
          this.addCheckedCategoryToPlainList(this.checkedCategories, category);
        });
        this.addCategoryToList();
        break;
    }
  }

  addCheckedCategoryToPlainList(list: string[], category: Category) {
    if (category.isChecked) {
      list.push(category.name);
      category.childCategories.forEach((child) =>
        this.addCheckedCategoryToPlainList(list, child)
      );
    }
  }

  addCategoryToList() {
    this.allProducts.forEach((product) => {
      if (
        null != product.productCategory &&
        this.checkedCategories.includes(product.productCategory.name)
      ) {
        this.displayedProducts.push(product);
      }
    });
  }

  navigateToComponentForm(productId : number) {
    this.router.navigate(['/products', productId]);
  }

}
