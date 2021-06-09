import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OppositionCarteComponent } from './opposition-carte.component';

describe('OppositionCarteComponent', () => {
  let component: OppositionCarteComponent;
  let fixture: ComponentFixture<OppositionCarteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OppositionCarteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OppositionCarteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
