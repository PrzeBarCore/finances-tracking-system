import { AfterViewInit, Component } from '@angular/core';
import { Receipt, ReceiptItem } from '../model/receipt';
import { HtmlTagDefinition } from '@angular/compiler';
import { Account } from 'src/app/account/model/account';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/account/service/account.service';
import { Category, CategoryType } from 'src/app/category/model/category';
import { Product } from 'src/app/product/model/product';
import { ReceiptService } from '../service/receipt.service';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  styleUrls: ['./receipt-form.component.scss'],
  exportAs: 'receiptForm',
})
export class ReceiptFormComponent implements AfterViewInit {
  receiptCategoryType = CategoryType.Receipt;
  openedFromAccountComponent = false;

  accountList: Account[] = [];
  account: Account = new Account(0, '', 0, '', []);

  receiptItems: ReceiptItem[] = [
    new ReceiptItem(
      -1,
      '',
      1,
      0,
      0,
      new Category(-1, '', '', [], null, false),
      new Product(-1, '', '', 0, '', 0, null, null)
    ),
  ];
  receipt: Receipt = new Receipt(
    -1,
    this.account,
    new Date(),
    0.0,
    0.0,
    this.receiptItems
  );
  currentlyEditedItem: ReceiptItem = this.receiptItems[0];

  isAnyItemEdited = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private accountService: AccountService,
    private receiptService: ReceiptService,
    private router: Router
  ) {
    accountService.getAccounts().subscribe((response) => {
      this.accountList = response;
      if (activatedRoute.snapshot.paramMap.get('aid')) {
        let foundAccount = this.accountList.find(
          (account) =>
            account.id.toString() ==
            activatedRoute.snapshot.paramMap.get('aid')!
        );
        if (foundAccount != null) {
          this.account = foundAccount;
          this.openedFromAccountComponent = true;
        }
      }
    });
  }
  ngAfterViewInit(): void {
    this.calculateTotalValue();
    this.toggleEdition(0);
  }

  saveReceipt() {
    this.receipt.sourceAccount = this.account;
    this.receiptService.saveReceipt(this.receipt).subscribe((response) => {
      if (response.status == 200) {
        if (this.openedFromAccountComponent)
          this.router.navigate(['/accounts/details/' + this.account.id]);
        else this.router.navigate(['/receipts/']);
      }
    });
  }

  //Data Maniupulation
  removeItem(indexOfReceipt: number) {
    if (this.receipt.items[indexOfReceipt].isEdited) {
      this.toggleEdition(indexOfReceipt);
    }
    this.receipt.items.splice(indexOfReceipt, 1);
    this.calculateTotalValue();
  }

  saveItem(indexOfReceipt: number) {
    this.toggleEdition(indexOfReceipt);
    this.calculateTotalValue();
  }

  addItem() {
    let newItem = new ReceiptItem(
      -1,
      '',
      1,
      0,
      0,
      new Category(0, '', '', [], null, false),
      new Product(0, '', '', 0, '', 0, null, null)
    );
    newItem.isEdited = true;
    this.receipt.items.push(newItem);
    this.currentlyEditedItem = newItem;
    this.setButtonsState(false);
  }

  categoryForItemSelected(newCategory: Category) {
    this.currentlyEditedItem.category = newCategory;
  }

  productForItemSelected(newProduct: Product) {
    if (this.currentlyEditedItem.name == '')
      this.currentlyEditedItem.name = newProduct.name;
    if (this.currentlyEditedItem.regularPrice == 0)
      this.currentlyEditedItem.regularPrice = newProduct.defaultPrice;
    if (newProduct.defaultReceiptTransactionCategory != null)
      this.currentlyEditedItem.category =
        newProduct.defaultReceiptTransactionCategory;
    this.currentlyEditedItem.product = newProduct;
  }

  calculateTotalValue() {
    let totalValue = 0;
    let totalDiscount = 0;
    this.receiptItems.forEach((item) => {
      totalValue += item.regularPrice * item.quantity - item.discount;
      totalDiscount += item.discount;
    });

    this.receipt.totalValue = totalValue;
    this.receipt.totalDiscount = totalDiscount;
  }

  //Layout methods
  toggleEdition(indexOfReceipt: number) {
    let isEdited = this.receipt.items[indexOfReceipt].isEdited;
    if (isEdited) {
      this.currentlyEditedItem = this.receipt.items[indexOfReceipt];
    }
    this.isAnyItemEdited = this.receipt.items[indexOfReceipt].isEdited =
      !isEdited;

    this.updateLayout(indexOfReceipt, isEdited);
  }

  updateLayout(indexOfReceipt: number, isEdited: boolean) {
    let buttonsFromEditedRow = <HTMLCollection>(
      document
        .getElementsByTagName('tr')
        [indexOfReceipt].children.namedItem('buttons')?.children
    );
    if (null != buttonsFromEditedRow) {
      for (let index = 0; index < buttonsFromEditedRow.length; index++) {
        const element = <HTMLButtonElement>buttonsFromEditedRow[index];
        if (!isEdited) {
          element.classList.add('edited');
        } else {
          element.classList.remove('edited');
        }
      }
    }

    this.setButtonsState(isEdited);
  }

  setButtonsState(state: boolean) {
    let buttons = <HTMLCollection>(
      document.getElementsByClassName('receipt-btn')
    );
    for (let index = 0; index < buttons.length; index++) {
      const element = <HTMLButtonElement>buttons[index];
      if (!element.classList.contains('edited')) {
        element.disabled = !state;
      }
    }
  }
}
