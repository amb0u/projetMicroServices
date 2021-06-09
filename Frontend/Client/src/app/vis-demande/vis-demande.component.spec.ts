import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisDemandeComponent } from './vis-demande.component';

describe('VisDemandeComponent', () => {
  let component: VisDemandeComponent;
  let fixture: ComponentFixture<VisDemandeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisDemandeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisDemandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
