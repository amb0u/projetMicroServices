import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionListBarComponent } from './transaction-list-bar.component';

describe('TransactionListBarComponent', () => {
  let component: TransactionListBarComponent;
  let fixture: ComponentFixture<TransactionListBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransactionListBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionListBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
