import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisCustomerComponent } from './vis-customer.component';

describe('VisCustomerComponent', () => {
  let component: VisCustomerComponent;
  let fixture: ComponentFixture<VisCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
