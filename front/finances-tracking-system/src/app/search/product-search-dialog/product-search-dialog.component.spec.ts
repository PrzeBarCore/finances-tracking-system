import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductSearchDialogComponent } from './product-search-dialog.component';

describe('ProductSearchDialogComponent', () => {
  let component: ProductSearchDialogComponent;
  let fixture: ComponentFixture<ProductSearchDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductSearchDialogComponent]
    });
    fixture = TestBed.createComponent(ProductSearchDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
