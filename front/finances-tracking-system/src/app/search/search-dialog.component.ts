import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnChanges,
  Query,
  SimpleChanges,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'search-dialog',
  templateUrl: './search-dialog.component.html',
  styleUrls: ['./search-dialog.component.scss'],
})
export class SearchDialogComponent {
  @ViewChild('dialog', { static: true }) dialog: ElementRef | null = null;
  constructor() {}

  public open(): void {
    this.dialog?.nativeElement.classList.remove('hidden');
    this.dialog?.nativeElement.showModal();
  }

  close(): void {
    this.dialog?.nativeElement.close();
    this.dialog?.nativeElement.classList.add('hidden');
  }
}
