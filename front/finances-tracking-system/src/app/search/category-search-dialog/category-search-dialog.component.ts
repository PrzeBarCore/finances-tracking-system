import {
  AfterViewInit,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { Category, CategoryType } from 'src/app/category/model/category';
import { CategoryDataService } from 'src/app/category/services/category-data.service';
import { SearchDialogComponent } from '../search-dialog.component';

@Component({
  selector: 'category-search-dialog',
  templateUrl: './category-search-dialog.component.html',
  styleUrls: ['./category-search-dialog.component.scss'],
})
export class CategorySearchDialogComponent implements OnChanges, AfterViewInit {
  @Input() initialCategory: Category | null = null;
  @Input() categoryType: string | null = null;
  @Output() selectedCategoryChanged = new EventEmitter<Category>();
  @ViewChild('searchDialog', { static: true })
  dialog!: SearchDialogComponent;

  data: Category[] = [];
  isDataLoaded: boolean = false;
  displayedData: {}[] = [];
  currentlySelectedCategory: {} = {};

  constructor(private categoryService: CategoryDataService) {}
  ngAfterViewInit(): void {
    this.dialog.close();
  }

  ngOnChanges(): void {
    if (
      !this.isDataLoaded &&
      this.categoryType != null &&
      CategoryType.isStringCategoryType(this.categoryType)
    ) {
      this.categoryService
        .getAllCategoriesOfType(this.categoryType)
        .subscribe((response) => {
          this.data = response;
          this.displayedData = this.data.map((category) => {
            return { name: category.name };
          });
        });
      this.isDataLoaded = true;
    }

    if (this.initialCategory != null && this.initialCategory.name != '') {
      this.currentlySelectedCategory = { name: this.initialCategory.name };
    }
  }

  nameFilterChanged(nameFilter: string) {
    this.displayedData = this.data
      .filter((category) =>
        category.name
          .toLocaleLowerCase()
          .includes(nameFilter.toLocaleLowerCase())
      )
      .map((category) => {
        return { name: category.name };
      });
  }

  changeCurentlySelectedCategory(category: {}) {
    this.currentlySelectedCategory = category;
  }

  saveSelectedCategory() {
    if (Object.values(this.currentlySelectedCategory).length > 0) {
      this.selectedCategoryChanged.emit(
        this.data.find(
          (category) =>
            category.name == Object.values(this.currentlySelectedCategory)[0]
        )
      );
      this.dialog.close();
    }
  }

  open() {
    if (this.initialCategory == null || this.initialCategory.name == '') {
      this.currentlySelectedCategory = {};
    }
    this.dialog.open();
  }

  clearFilter(input: HTMLInputElement) {
    input.value = '';
    this.nameFilterChanged('');
  }
}
