import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VisRepComponent } from './vis-rep.component';

describe('VisRepComponent', () => {
  let component: VisRepComponent;
  let fixture: ComponentFixture<VisRepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VisRepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VisRepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
