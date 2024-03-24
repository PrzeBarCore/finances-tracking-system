export class GlobalRoutes {
  public static ACCOUNT_LIST = 'accounts';
  public static ACCOUNT_FORM = 'accounts/:id';
  public static ACCOUNT_DETAILS = 'accounts/:id/details';
  public static RECEIPT_FORM = 'transactions/receipt/:id';
  public static CATEGORY_LIST = 'categories/:type';
  public static CATEGORY_FORM = 'categories/:type/:id';
  public static PRODUCT_LIST = 'products';
  public static PRODUCT_FORM = 'products/:id';
  public static TRANSACTION_FORM = 'transactions/:id';

  static ID = ':id';
  static TYPE = ':type';
  public static getUrlForComponent(
    componentType: ComponentType,
    params: string[] = []
  ) {
    switch (componentType) {
      case ComponentType.ACCOUNT_LIST:
        return this.ACCOUNT_LIST;
      case ComponentType.ACCOUNT_FORM:
        if (params.length > 0)
          return this.ACCOUNT_FORM.replace(this.ID, params[0]);
        break;
      case ComponentType.ACCOUNT_DETAILS:
        if (params.length > 0)
          return this.ACCOUNT_DETAILS.replace(this.ID, params[0]);
        break;
      case ComponentType.RECEIPT_FORM:
        if (params.length > 0)
          return this.RECEIPT_FORM.replace(this.ID, params[0]);
        break;
      case ComponentType.CATEGORY_LIST:
        if (params.length > 0)
          return this.CATEGORY_LIST.replace(this.ID, params[0]);
        break;
      case ComponentType.CATEGORY_FORM:
        if (params.length > 1)
          return this.CATEGORY_FORM.replace(this.ID, params[0]).replace(
            this.TYPE,
            params[1]
          );
        break;
      case ComponentType.PRODUCT_LIST:
        return this.PRODUCT_LIST;
      case ComponentType.PRODUCT_FORM:
        if (params.length > 0)
          return this.PRODUCT_FORM.replace(this.ID, params[0]);
        break;
      case ComponentType.TRANSACTION_FORM:
        if (params.length > 0)
          return this.TRANSACTION_FORM.replace(this.ID, params[0]);
        break;
    }
    return '';
  }
}

export enum ComponentType {
  ACCOUNT_LIST,
  ACCOUNT_FORM,
  ACCOUNT_DETAILS,
  RECEIPT_FORM,
  CATEGORY_LIST,
  CATEGORY_FORM,
  PRODUCT_LIST,
  PRODUCT_FORM,
  TRANSACTION_FORM,
}
