import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardsWalletComponent } from './cards-wallet.component';

describe('CardsWalletComponent', () => {
  let component: CardsWalletComponent;
  let fixture: ComponentFixture<CardsWalletComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardsWalletComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardsWalletComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
