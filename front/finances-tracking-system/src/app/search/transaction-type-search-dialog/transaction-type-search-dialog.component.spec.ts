import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionTypeSearchDialogComponent } from './transaction-type-search-dialog.component';

describe('TransactionTypeSearchDialogComponent', () => {
  let component: TransactionTypeSearchDialogComponent;
  let fixture: ComponentFixture<TransactionTypeSearchDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TransactionTypeSearchDialogComponent]
    });
    fixture = TestBed.createComponent(TransactionTypeSearchDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
