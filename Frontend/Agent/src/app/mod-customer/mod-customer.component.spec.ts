import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModCustomerComponent } from './mod-customer.component';

describe('ModCustomerComponent', () => {
  let component: ModCustomerComponent;
  let fixture: ComponentFixture<ModCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
