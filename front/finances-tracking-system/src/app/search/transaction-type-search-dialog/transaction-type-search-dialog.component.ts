import {
  AfterViewInit,
  Component,
  EventEmitter,
  Output,
  ViewChild,
} from '@angular/core';
import { SearchDialogComponent } from '../search-dialog.component';
import { CommonDataService } from 'src/app/common/services/common-data.service';
import { Transaction } from 'src/app/common/model/transaction';

@Component({
  selector: 'transaction-type-search-dialog',
  templateUrl: './transaction-type-search-dialog.component.html',
  styleUrls: ['./transaction-type-search-dialog.component.scss'],
})
export class TransactionTypeSearchDialogComponent implements AfterViewInit {
  @Output() selectedTypeChanged = new EventEmitter<string>();
  @ViewChild('transactionTypeSearchDialog', { static: true })
  dialog!: SearchDialogComponent;

  data: string[] = [];
  displayedData: {}[] = [];
  currentlySelectedType: string = '';

  constructor(private commonService: CommonDataService) {}
  ngAfterViewInit(): void {
    this.dialog.close();

    this.commonService.getTransactionTypes().subscribe((response) => {
      this.data = response;
      this.displayedData = this.data.map((type) => {
        return { name: Transaction.getNormalizedTransactionType(type) };
      });
      this.currentlySelectedType = this.data[0];
    });
  }

  open() {
    this.dialog.open();
  }

  changeCurentlySelectedType(type: string) {
    this.currentlySelectedType = type;
  }

  saveSelectedType() {
    if (Object.values(this.currentlySelectedType).length > 0) {
      this.selectedTypeChanged.emit(
        this.data.find((type) => {
          return (
            Transaction.getNormalizedTransactionType(type) ==
            Object.values(this.currentlySelectedType)[0]
          );
        })
      );
      this.dialog.close();
    }
  }
}
