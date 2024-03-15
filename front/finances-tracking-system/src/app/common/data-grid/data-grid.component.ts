import { CommonModule, KeyValue } from '@angular/common';
import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  QueryList,
  ViewChildren,
} from '@angular/core';

@Component({
  selector: 'data-grid',
  templateUrl: './data-grid.component.html',
  styleUrls: ['./data-grid.component.scss'],
  standalone: true,
  imports: [CommonModule],
})
export class DataGridComponent implements OnChanges {
  @Input() selectedRow: {} = {};
  @Input() dataSource: {}[] = [];
  @Output() selectedRowChange = new EventEmitter<any>();
  @ViewChildren('checkboxes') checkboxes: QueryList<ElementRef> =
    new QueryList();
  headerList: string[] = [];
  hashKey: string = '';
  //overriding angular keyvalue pipe ordering
  readonly originalOrder = (): number => 0;

  constructor(private cdr: ChangeDetectorRef) {
    if (this.selectedRow != null) this.toggleCheckboxForRow(this.selectedRow);
  }

  ngOnChanges(): void {
    if (this.dataSource[0] != null) {
      if (this.hashKey == '') this.createHashKey();
      this.headerList = Object.keys(this.dataSource[0]);
    }
    this.cdr.detectChanges();
    if (!this.areObjectsEquals(this.selectedRow, {})) {
      this.toggleCheckboxForRow(this.selectedRow);
    } else {
      this.checkboxes.forEach(
        (checkbox) => (checkbox.nativeElement.checked = false)
      );
    }
  }

  rowClicked(id: number) {
    let checkbox = <HTMLInputElement>(
      document.getElementById('checkbox' + this.hashKey + id)
    );
    if (checkbox != null) {
      let wasChecked = checkbox.checked;
      this.checkboxes.forEach(
        (checkbox) => (checkbox.nativeElement.checked = false)
      );
      this.setSelectedRow({});
      if (!wasChecked) {
        checkbox.checked = true;
        this.setSelectedRow(this.dataSource[id]);
      }
    }
  }

  areObjectsEquals(row1: {}, row2: {}): boolean {
    let row1Values = Object.values(row1);
    let row1Keys = Object.keys(row1);
    let row2Values = Object.values(row2);
    let row2Keys = Object.keys(row2);
    if (
      row1Values.length != row2Values.length ||
      row1Keys.length != row2Keys.length
    )
      return false;

    for (let i = 0; i < row1Keys.length; i++) {
      if (row1Values[i] != row2Values[i] || row1Keys[i] != row2Keys[i])
        return false;
    }
    return true;
  }

  setSelectedRow(newValue: {}) {
    this.selectedRow = newValue;
    this.selectedRowChange.emit(this.selectedRow);
  }

  toggleCheckboxForRow(selectedRowToCheck: {}) {
    if (
      this.dataSource.some((row) => {
        if (this.areObjectsEquals(selectedRowToCheck, row)) {
          this.setSelectedRow(row);
          return true;
        }
        return false;
      })
    ) {
      let id = this.dataSource.findIndex((row) =>
        this.areObjectsEquals(row, selectedRowToCheck)
      );
      let checkbox = this.checkboxes.filter(
        (element) => element.nativeElement.id == 'checkbox' + this.hashKey + id
      );
      if (checkbox.length != 0) {
        checkbox[0].nativeElement.checked = true;
      }
    }
  }

  createHashKey() {
    this.hashKey =
      Object.values(this.dataSource[0]).toString().slice(0, 10) + Date.now();
  }
}
