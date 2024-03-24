import { Component } from '@angular/core';
import { Category, CategoryType } from '../model/category';
import { ActivatedRoute, Router } from '@angular/router';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { CategoryDataService } from '../services/category-data.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.scss'],
})
export class CategoryFormComponent {
  setAccount(arg0: any) {
    throw new Error('Method not implemented.');
  }
  categoryList = [
    CategoryType.Account,
    CategoryType.Product,
    CategoryType.Receipt,
    CategoryType.Transaction,
  ];

  treeControl = new NestedTreeControl<Category>((node) => node.childCategories);
  dataSource = new MatTreeNestedDataSource<Category>();
  categoriesToSelect: Category[] = [];
  categoryInstance: Category = new Category(
    
    -1,
    '',
    CategoryType.translateUrlParam(this.activatedRoute.snapshot.params['type']),
    [],
    null,
    false
  );
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private dataService: CategoryDataService
  ) {
    this.dataSource.data = this.categoryInstance.childCategories;
    this.dataService
      .getAllCategoriesOfType(this.categoryInstance.categoryType)
      .subscribe((response) => {
        this.categoriesToSelect = response;
      });
  }

  saveCategory() {
    console.log(this.categoryInstance);
    this.dataService.createCategory(this.categoryInstance).subscribe(() => {
      this.router.navigate([
        `categories/${this.categoryInstance.categoryType}`,
      ]);
    });
  }

  findParentOfCategory(
    subcategory: Category,
    parentCategory: Category
  ): Category | undefined {
    let result = undefined;
    if (parentCategory.hasAnyChild) {
      parentCategory.childCategories.forEach((element) => {
        if (element.name === subcategory.name) result = parentCategory;
      });

      parentCategory.childCategories.forEach((element) => {
        if (element.hasAnyChild)
          result = this.findParentOfCategory(subcategory, element);
      });
    }
    return result;
  }

  addSubcategory(category: Category) {
    if (!category.hasAnyChild) category.hasAnyChild = true;
    let newCategory = new Category(
      -1,
      '',
      this.categoryInstance.categoryType,
      [],
      -1,
      false
    );
    newCategory.isEdited = true;
    category.childCategories.push(newCategory);
    this.refreshDataSource();

    this.setButtonsState(false);
  }

  deleteSubcategory(category: Category) {
    let parent = this.findParentOfCategory(category, this.categoryInstance);
    console.log(parent);
    if (parent != undefined)
      parent.childCategories.splice(
        parent.childCategories.findIndex((cat) => cat.name === category.name),
        1
      );
    category.isEdited = false;
    this.refreshDataSource();
    this.setButtonsState(true);
  }

  acceptChanges(category: Category) {
    category.isEdited = false;
    this.setButtonsState(true);
  }

  setEditMode(category: Category) {
    category.isEdited = true;
    this.refreshDataSource();
    this.setButtonsState(false);
  }

  setButtonsState(state: boolean) {
    let buttons = <HTMLCollection>document.getElementsByClassName('btn');
    for (let index = 0; index < buttons.length; index++) {
      const element = <HTMLButtonElement>buttons[index];
      if (!element.classList.contains('edited')) {
        element.disabled = !state;
      }
    }
  }

  refreshDataSource() {
    this.dataSource.data = [];
    this.dataSource.data = this.categoryInstance.childCategories;
  }

  hasChild = (_: number, node: Category) =>
    !!node.childCategories && node.childCategories.length > 0;
}
