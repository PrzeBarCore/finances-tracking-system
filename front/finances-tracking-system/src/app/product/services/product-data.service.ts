import { HttpClient, HttpStatusCode } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root',
})
export class ProductDataService {
  constructor(private httpClient: HttpClient) {}

  getProducts() {
    return this.httpClient.get<Product[]>(`http://localhost:8080/products`);
  }

  getProduct(productId: number) {
    return this.httpClient.get<Product>(
      `http://localhost:8080/products/${productId}`
    );
  }

  createProduct(productToCreate: Product) {
    return this.httpClient.post(
      `http://localhost:8080/products`,
      productToCreate,
      { observe: 'response' }
    );
  }

  updateProduct(productId: number, productToUpdate: Product) {
    return this.httpClient.put(
      `http://localhost:8080/products/${productId}`,
      productToUpdate,
      { observe: 'response' }
    );
  }

  deleteProduct(productId: number) {
    return this.httpClient.delete(
      `http://localhost:8080/products/${productId}`
    );
  }
}
