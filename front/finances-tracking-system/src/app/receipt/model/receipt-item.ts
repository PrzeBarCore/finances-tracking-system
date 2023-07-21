export class ReceiptItem {
  name: string;
  quantity: number;
  regularPrice: number;
  discount: number;
  productCategoryId: number;
  productId: number;

  constructor(
    name: string,
    quantity: number,
    regularPrice: number,
    discount: number,
    productCategoryId: number,
    productId: number
  ) {
    this.name = name;
    this.quantity = quantity;
    this.regularPrice = regularPrice;
    this.discount = discount;
    this.productCategoryId = productCategoryId;
    this.productId = productId;
  }
}
