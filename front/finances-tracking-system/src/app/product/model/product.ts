import { Category } from 'src/app/category/model/category';

export class Product {
  constructor(
    public id: number,
    public producer: string,
    public name: string,
    public quantity: number,
    public unit: string,
    public defaultPrice: number,
    public productCategory: Category | null,
    public defaultReceiptTransactionCategory: Category | null
  ) {}
}
