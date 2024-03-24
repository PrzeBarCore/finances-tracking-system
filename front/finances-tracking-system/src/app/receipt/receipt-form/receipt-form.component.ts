import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Receipt, ReceiptItem } from '../model/receipt';
import { SimpleAccount } from 'src/app/account/model/account';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/account/service/account.service';
import { Category, CategoryType } from 'src/app/category/model/category';
import { Product } from 'src/app/product/model/product';
import { ReceiptTransaction } from 'src/app/common/model/receiptTransaction';
import { TransactionService } from 'src/app/transaction/service/transaction.service';
import {
  ComponentType,
  GlobalRoutes,
} from 'src/app/common/globals/globalRoutes';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  styleUrls: ['./receipt-form.component.scss'],
  exportAs: 'receiptForm',
})
export class ReceiptFormComponent implements AfterViewInit {
  @ViewChild('accountSelection', { static: true })
  accountSelect!: HTMLSelectElement;
  receiptCategoryType = CategoryType.Receipt;

  accountList: SimpleAccount[] = [];
  receiptTransaction = new ReceiptTransaction(
    -1,
    new Date(),
    0.0,
    new SimpleAccount(-1, ''),
    new Receipt(-1, 0.0, [
      new ReceiptItem(
        -1,
        '',
        1,
        0,
        0,
        new Category(-1, '', '', [], null, false),
        new Product(-1, '', '', 0, '', 0, null, null)
      ),
    ])
  );

  currentlyEditedItem: ReceiptItem = this.receiptTransaction.receipt.items[0];

  isAnyItemEdited = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private accountService: AccountService,
    private transactionService: TransactionService,
    private router: Router
  ) {
    let passedReceiptId = activatedRoute.snapshot.paramMap.get('id');
    if (passedReceiptId != null && passedReceiptId != '-1') {
      transactionService
        .findReceipt(Number.parseInt(passedReceiptId!))
        .subscribe((response) => {
          this.receiptTransaction = response;
        });
    }
    accountService.getAccounts().subscribe((response) => {
      this.accountList = response;
      if (activatedRoute.snapshot.paramMap.get('aid')) {
        let foundAccount = this.accountList.find(
          (account) =>
            account.id.toString() ==
            activatedRoute.snapshot.paramMap.get('aid')!
        );
        if (foundAccount != null) {
          this.receiptTransaction.sourceAccount = foundAccount;
        }
      }
    });
  }
  ngAfterViewInit(): void {
    this.calculateTotalValue();
    this.toggleEdition(0);
  }

  saveReceipt() {
    this.transactionService
      .saveReceipt(this.receiptTransaction)
      .subscribe((response) => {
        if (response.status == 200) {
          this.router.navigate([
            GlobalRoutes.getUrlForComponent(ComponentType.ACCOUNT_DETAILS, [
              `${this.receiptTransaction.sourceAccount!.id}`,
            ]),
          ]);
        }
      });
  }

  //Data Maniupulation
  removeItem(indexOfReceipt: number) {
    if (this.receiptTransaction.receipt.items[indexOfReceipt].isEdited) {
      this.toggleEdition(indexOfReceipt);
    }
    this.receiptTransaction.receipt.items.splice(indexOfReceipt, 1);
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
    this.receiptTransaction.receipt.items.push(newItem);
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
    this.receiptTransaction.receipt.items.forEach((item) => {
      totalValue += item.regularPrice * item.quantity - item.discount;
      totalDiscount += item.discount;
    });

    this.receiptTransaction.totalValue = totalValue;
    this.receiptTransaction.receipt.totalDiscount = totalDiscount;
  }

  //Layout methods
  toggleEdition(indexOfReceipt: number) {
    let isEdited =
      this.receiptTransaction.receipt.items[indexOfReceipt].isEdited;
    if (isEdited) {
      this.currentlyEditedItem =
        this.receiptTransaction.receipt.items[indexOfReceipt];
    }
    this.isAnyItemEdited = this.receiptTransaction.receipt.items[
      indexOfReceipt
    ].isEdited = !isEdited;

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
