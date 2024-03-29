export class Category {
  public isEdited = false;
  public isChecked = true;
  constructor(
    public id: number,
    public name: string,
    public categoryType: string,
    public childCategories: Category[],
    public parentCategoryId: number | null,
    public hasAnyChild: boolean
  ) {}
}

export class CategoryType {
  public static readonly Transaction = 'TRANSACTION';
  public static readonly Account = 'ACCOUNT';
  public static readonly Receipt = 'RECEIPT';
  public static readonly Product = 'PRODUCT';

  public static isStringCategoryType(stringToCheck: string) {
    if (
      stringToCheck == this.Transaction ||
      stringToCheck == this.Account ||
      stringToCheck == this.Receipt ||
      stringToCheck == this.Product
    )
      return true;
    return false;
  }

  public static translateUrlParam(urlParam: string): string {
    let result = '';

    switch (urlParam.toLowerCase()) {
      case this.Transaction.toLowerCase():
        result = this.Transaction;
        break;
      case this.Account.toLowerCase():
        result = this.Account;
        break;
      case this.Receipt.toLowerCase():
        result = this.Receipt;
        break;
      case this.Product.toLowerCase():
        result = this.Product;
    }
    return result;
  }
}
