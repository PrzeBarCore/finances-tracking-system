import { Component, Input } from '@angular/core';
import { CategoryDataService } from './services/category-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from './model/category';
import { CategoryTreeEvent } from './category-tree/category-tree.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss'],
})
export class CategoryComponent {
  categoryType: string = this.activatedRoute.snapshot.params['type'];
  public categories: Category[] = [];

  constructor(
    private dataService: CategoryDataService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    activatedRoute.params.subscribe((params) => {
      if (this.categoryType != params['type'])
        this.categoryType = params['type'];
      this.getCategoriesOfType(this.categoryType);
    });
  }

  receiveMessage(event: CategoryTreeEvent) {
    switch (event.state) {
      case 'delete':
        this.deleteCategory(event.id);
        break;
    }
  }

  deleteCategory(categoryId: number) {
    this.dataService
      .deleteCategory(categoryId)
      .subscribe(() => this.getCategoriesOfType(this.categoryType));
  }

  navigateToComponentForm() {
    this.router.navigate(['/categories', this.categoryType, '-1']);
  }

  getCategoriesOfType(categoryType: string) {
    this.dataService
      .getRootCategoriesOfType(categoryType)
      .subscribe((response) => {
        this.categories = response;
      });
  }

  // findCategoryInArray(
  //   categoryId: number,
  //   categoryArray: Category[]
  // ): Category | null {
  //   let returnValue = null;
  //   categoryArray.forEach((category) => {
  //     if (category.id === categoryId) returnValue = category;
  //     else if (category.hasAnyChild) {
  //       let result = this.findCategoryInArray(
  //         categoryId,
  //         category.childCategories
  //       );
  //       if (null != result) returnValue = result;
  //     }
  //   });
  //   return returnValue;
  // }

  // updateButtonsState() {
  //   let buttons = <HTMLCollection>document.getElementsByClassName('btn');
  //   for (let index = 0; index < buttons.length; index++) {
  //     const element = <HTMLButtonElement>buttons[index];
  //     if (!element.classList.contains('edited')) {
  //       element.disabled = !element.disabled;
  //     }
  //   }
  // }
}
