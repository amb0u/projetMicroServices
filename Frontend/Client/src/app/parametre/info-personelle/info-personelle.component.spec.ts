import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPersonelleComponent } from './info-personelle.component';

describe('InfoPersonelleComponent', () => {
  let component: InfoPersonelleComponent;
  let fixture: ComponentFixture<InfoPersonelleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoPersonelleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoPersonelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
