export class Receipt {
  constructor(
    public id: number,
    public issuedOnDate: Date,
    public totalValue: number,
    public totalDiscount: number,
    public listOfItems: ReceiptItem[]
  ) {}
}

export class ReceiptItem {
  constructor(
    public name: string,
    public quantity: number,
    public regularPrice: number,
    public discount: number,
    public productCategoryId: number,
    public productId: number,
    public isEdited: boolean
  ) {}
}
