import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core';
import { Product } from 'src/app/product/model/product';
import { DialogComponent, DialogType } from '../dialog.component';
import { ProductDataService } from 'src/app/product/services/product-data.service';

@Component({
  selector: 'product-search-dialog',
  templateUrl: './product-search-dialog.component.html',
  styleUrls: ['./product-search-dialog.component.scss'],
})
export class ProductSearchDialogComponent {
  @Input() initialProduct: Product | null = null;
  @Output() selectedProductChanged = new EventEmitter<Product>();
  @ViewChild('productSearchDialog', { static: true })
  dialog!: DialogComponent;
  dialogType: DialogType = DialogType.SEARCH;
  @ViewChild('productName', { static: true })
  productName!: ElementRef;
  @ViewChild('producer', { static: true })
  producer!: ElementRef;

  data: Product[] = [];
  displayedData: {}[] = [];
  currentlySelectedProduct: PlainProduct = new PlainProduct();

  constructor(private productService: ProductDataService) {}
  ngAfterViewInit(): void {
    this.dialog.close();
  }

  filterChanged() {
    this.displayedData = this.data
      .filter((product) => {
        if (
          this.productName.nativeElement.value == '' &&
          this.producer.nativeElement.value == ''
        )
          return true;
        let result = false;
        if (this.productName.nativeElement.value != '')
          result = product.name
            .toLocaleLowerCase()
            .includes(this.productName.nativeElement.value.toLocaleLowerCase());

        if (this.producer.nativeElement.value != '')
          result =
            product.producer
              .toLocaleLowerCase()
              .includes(
                this.producer.nativeElement.value.toLocaleLowerCase()
              ) && result;
        return result;
      })
      .map((product) => PlainProduct.fromProduct(product));
  }

  saveSelectedProduct() {
    if (Object.values(this.currentlySelectedProduct).length > 0) {
      this.selectedProductChanged.emit(
        this.data.find((product) =>
          this.currentlySelectedProduct.equalsProduct(product)
        )
      );
      this.dialog.close();
    }
  }

  open() {
    this.productService.getProducts().subscribe((response) => {
      this.data = response;
      this.displayedData = this.data.map((product) => {
        return PlainProduct.fromProduct(product);
      });
    });
    if (this.initialProduct == null || this.initialProduct.name == '') {
      this.currentlySelectedProduct = new PlainProduct();
    }
    this.dialog.open();
  }

  clearFilter() {
    this.producer.nativeElement.value = this.productName.nativeElement.value =
      '';
    this.filterChanged();
  }
}

class PlainProduct {
  public name: string = '';
  public producer: string = '';
  public quantity: number = 0;
  public unit: string = '';
  public defaultPrice: number = 0;
  constructor() {}

  static fromProduct(product: Product): PlainProduct {
    let newInstance = new PlainProduct();
    newInstance.name = product.name;
    newInstance.producer = product.producer;
    newInstance.quantity = product.quantity;
    newInstance.unit = product.unit;
    newInstance.defaultPrice = product.defaultPrice;
    return newInstance;
  }

  equalsProduct(product: Product) {
    if (
      this.name == product.name &&
      this.producer == product.producer &&
      this.quantity == product.quantity &&
      this.unit == product.unit &&
      this.defaultPrice == product.defaultPrice
    )
      return true;
    return false;
  }
}
