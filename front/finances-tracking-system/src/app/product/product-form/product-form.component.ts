import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { Product } from '../model/product';
import { ProductDataService } from '../services/product-data.service';
import { CommonDataService } from 'src/app/common/services/common-data.service';
import { CategoryType } from 'src/app/category/model/category';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
})
export class ProductFormComponent {
  productInstance: Product = new Product(-1, '', '', 0.0, '', 0.0, null, null);
  receiptCategoryType = CategoryType.Receipt;
  productCategoryType = CategoryType.Product;
  unitList: string[] = [];
  constructor(
    private activatedRouter: ActivatedRoute,
    private productDataService: ProductDataService,
    private commonDataService: CommonDataService,
    private router: Router
  ) {
    let productId = this.activatedRouter.snapshot.params['id'];
    if (-1 != productId) {
      productDataService.getProduct(productId).subscribe((response) => {
        console.log(response);
        this.productInstance = response;
      });
    }
    commonDataService
      .getUnits()
      .subscribe((response) => (this.unitList = response));
  }

  saveForm() {
    if (this.productInstance.id != -1) {
      this.productDataService
        .updateProduct(this.productInstance.id, this.productInstance)
        .subscribe((response) => {
          if (response.status == 200) {
            this.router.navigate(['/products']);
          }
        });
    } else {
      this.productDataService
        .createProduct(this.productInstance)
        .subscribe((response) => {
          if (response.status == 200) {
            this.router.navigate(['/products']);
          }
        });
    }
  }
}
