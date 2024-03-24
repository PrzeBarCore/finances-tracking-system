import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  Query,
  SimpleChanges,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'custom-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss'],
})
export class DialogComponent {
  @Input() textContent: string = '';
  @Input() dialogType: DialogType = DialogType.NOT_SPECIFIED;
  @Output() confirmButtonClicked = new EventEmitter<boolean>();
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

  confirm() {
    this.confirmButtonClicked.emit(true);
    this.close();
  }
  cancel() {
    this.confirmButtonClicked.emit(false);
    this.close();
  }
}

export enum DialogType {
  NOT_SPECIFIED,
  WARRNING,
  SEARCH,
}
