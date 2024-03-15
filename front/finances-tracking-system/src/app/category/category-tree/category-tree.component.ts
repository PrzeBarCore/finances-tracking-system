import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CategoryDataService } from '../services/category-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category, CategoryType } from '../model/category';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';

@Component({
  selector: 'app-category-tree',
  templateUrl: './category-tree.component.html',
  styleUrls: ['./category-tree.component.scss'],
})
export class CategoryTreeComponent {
  @Input() isReadOnly: boolean = false;
  @Input() categories: Category[] = [];
  @Output() messageEvent = new EventEmitter<CategoryTreeEvent>();

  dataSource = new MatTreeNestedDataSource<Category>();
  treeControl = new NestedTreeControl<Category>((node) => node.childCategories);

  constructor() {}

  ngOnChanges(): void {
    this.dataSource.data = this.categories;
  }

  deleteCategory(categoryId: number) {
    this.messageEvent.emit(new CategoryTreeEvent(categoryId, 'delete'));
  }

  findCategoryInArray(
    categoryId: number,
    categoryArray: Category[]
  ): Category | null {
    let returnValue = null;
    categoryArray.forEach((category) => {
      if (category.id === categoryId) returnValue = category;
      else if (category.hasAnyChild) {
        let result = this.findCategoryInArray(
          categoryId,
          category.childCategories
        );
        if (null != result) returnValue = result;
      }
    });
    return returnValue;
  }

  toggleCategoryById(categoryId: number, checkboxState: boolean) {
    let category = this.findCategoryInArray(categoryId, this.categories);
    if (null != category) {
      this.toggleCategory(category, checkboxState);
    }
    this.messageEvent.emit(new CategoryTreeEvent(0,"selectionChanged"))
  }

  toggleCategory(category: Category, checkboxState: boolean) {
    category.isChecked = checkboxState;
    category.childCategories.forEach((child) =>
      this.toggleCategory(child, checkboxState)
    );
  }

  hasChild = (_: number, node: Category) =>
    !!node.childCategories && node.childCategories.length > 0;

  updateButtonsState() {
    let buttons = <HTMLCollection>document.getElementsByClassName('btn');
    for (let index = 0; index < buttons.length; index++) {
      const element = <HTMLButtonElement>buttons[index];
      if (!element.classList.contains('edited')) {
        element.disabled = !element.disabled;
      }
    }
  }
}

export class CategoryTreeEvent {
  public id: number;
  public state: string;
  constructor(id: number, state: string) {
    this.id = id;
    this.state = state;
  }
}
