import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReceiptService } from '../service/receipt-service';
import { Receipt } from '../model/receipt';
import { ReceiptItem } from '../model/receipt-item';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  styleUrls: ['./receipt-form.component.scss'],
  exportAs: 'receiptForm',
})
export class ReceiptFormComponent {
  receipt: Receipt;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private receiptService: ReceiptService
  ) {
    this.receipt = new Receipt(new Date(), 0.0, false, []);
  }

  onSubmit() {
    this.receiptService
      .saveReceipt(this.receipt)
      .subscribe((result) => this.gotoReceiptList());
  }

  addItem() {
    this.receipt.listOfItems.push(new ReceiptItem('', 1, 0.0, 0.0, 0, 0));
  }

  gotoReceiptList() {
    this.router.navigate(['/receipts']);
  }
}
