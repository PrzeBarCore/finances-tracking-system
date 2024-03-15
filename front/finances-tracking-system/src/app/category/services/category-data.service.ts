import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category, CategoryType } from '../model/category';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryDataService {
  constructor(private httpClient: HttpClient) {}

  getRootCategoriesOfType(type: string) {
    return this.httpClient
      .get<Category[]>(`http://localhost:8080/categories/types/${type}`)
      .pipe(
        map((categories) => {
          categories.forEach((category) => this.initCategory(category));
          return categories;
        })
      );
  }

  getAllCategoriesOfType(type: string) {
    return this.httpClient.get<Category[]>(
      `http://localhost:8080/categories/types/${type}/all`
    );
  }

  deleteCategory(categoryId: number) {
    return this.httpClient.delete(
      `http://localhost:8080/categories/${categoryId}`
    );
  }

  createCategory(category: Category) {
    return this.httpClient.post(`http://localhost:8080/categories`, category);
  }

  private initCategory(category: Category) {
    category.isChecked = true;
    category.isEdited = false;
    category.childCategories.forEach((child) => this.initCategory(child));
  }
}
