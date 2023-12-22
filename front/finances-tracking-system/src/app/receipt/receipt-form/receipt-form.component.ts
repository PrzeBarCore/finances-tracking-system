import { Component } from '@angular/core';
import { Receipt, ReceiptItem } from '../model/receipt';
import { HtmlTagDefinition } from '@angular/compiler';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  styleUrls: ['./receipt-form.component.scss'],
  exportAs: 'receiptForm',
})
export class ReceiptFormComponent {
  receiptItems: ReceiptItem[] = [
    new ReceiptItem('Banany', 4, 5, 0, -1, -1, false),
    new ReceiptItem('Czekolada Wedel', 5, 2, 0, -1, -1, false),
    new ReceiptItem('Małpka', 15, 1, 0, -1, -1, false),
  ];
  receipt: Receipt = new Receipt(0, new Date(), 0.0, 0.0, this.receiptItems);
  isAnyItemEdited = true;

  ngOnInit() {
    this.calculateTotalValue();
  }

  removeItem(indexOfReceipt: number) {
    if (this.receipt.listOfItems[indexOfReceipt].isEdited) {
      this.toggleEdition(indexOfReceipt);
    }
    this.receipt.listOfItems.splice(indexOfReceipt, 1);
    this.calculateTotalValue();
  }

  saveItem(indexOfReceipt: number) {
    this.toggleEdition(indexOfReceipt);
    this.calculateTotalValue();
  }

  addItem() {
    this.receipt.listOfItems.push(
      new ReceiptItem('', 1, 0.0, 0.0, -1, -1, true)
    );
    this.setButtonsState(false);
  }

  saveReceipt() {}

  toggleEdition(indexOfReceipt: number) {
    let isEdited = this.receipt.listOfItems[indexOfReceipt].isEdited;
    this.isAnyItemEdited = this.receipt.listOfItems[indexOfReceipt].isEdited =
      !isEdited;

    this.updateLayout(indexOfReceipt, isEdited);
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
    let buttons = <HTMLCollection>document.getElementsByClassName('btn');
    for (let index = 0; index < buttons.length; index++) {
      const element = <HTMLButtonElement>buttons[index];
      if (!element.classList.contains('edited')) {
        element.disabled = !state;
      }
    }
  }
}
