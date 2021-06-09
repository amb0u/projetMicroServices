import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassRegenComponent } from './pass-regen.component';

describe('PassRegenComponent', () => {
  let component: PassRegenComponent;
  let fixture: ComponentFixture<PassRegenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassRegenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassRegenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
