import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategorySearchDialogComponent } from './category-search-dialog.component';

describe('CategorySearchDialogComponent', () => {
  let component: CategorySearchDialogComponent;
  let fixture: ComponentFixture<CategorySearchDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CategorySearchDialogComponent],
    });
    fixture = TestBed.createComponent(CategorySearchDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
