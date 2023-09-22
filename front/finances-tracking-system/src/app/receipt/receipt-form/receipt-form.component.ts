import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReceiptService } from '../service/receipt-service';
import { Receipt } from '../model/receipt';
import { ReceiptItem } from '../model/receipt-item';
import { FormControl, FormGroup } from '@angular/forms';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  styleUrls: ['./receipt-form.component.scss'],
  exportAs: 'receiptForm',
})
export class ReceiptFormComponent {
  receiptForm = new FormGroup({
    issuedOnDate: new FormControl(new Date().toISOString().substring(0, 10)),
    totalValue: new FormControl(0),
    isContainingListOfItems: new FormControl(false),
    listOfItems: new FormArray([
      new FormGroup({
        name: new FormControl(''),
        quantity: new FormControl(1),
        regularPrice: new FormControl(0),
        discount: new FormControl(0),
        productCategoryId: new FormControl(-1),
        productId: new FormControl(-1),
      }),
    ]),
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private receiptService: ReceiptService
  ) {}

  get items() {
    return this.receiptForm.get('listOfItems') as FormArray;
  }

  get date() {
    return this.receiptForm.get('issuedOnDate') as FormControl;
  }

  get totalValue() {
    return this.receiptForm.get('totalValue') as FormControl;
  }

  addItem() {
    debugger;
    this.items.push(
      new FormGroup({
        name: new FormControl(''),
        quantity: new FormControl(1),
        regularPrice: new FormControl(0),
        discount: new FormControl(0),
        productCategoryId: new FormControl(-1),
        productId: new FormControl(-1),
      })
    );
  }

  removeItem(i: number) {
    debugger;
    this.items.removeAt(i);
  }

  // private createNewItem() : FormArray{
  //
  //}

  gotoReceiptList() {
    this.router.navigate(['/receipts']);
  }

  onSubmit() {}
}
