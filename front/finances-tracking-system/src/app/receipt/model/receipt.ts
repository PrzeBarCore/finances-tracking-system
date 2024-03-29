import { Category } from 'src/app/category/model/category';
import { Product } from 'src/app/product/model/product';

export class Receipt {
  constructor(
    public id: number,
    public totalDiscount: number,
    public items: ReceiptItem[]
  ) {}
}

export class ReceiptItem {
  public isEdited = false;
  constructor(
    public id: number,
    public name: string,
    public quantity: number,
    public regularPrice: number,
    public discount: number,
    public category: Category,
    public product: Product
  ) {}
}
