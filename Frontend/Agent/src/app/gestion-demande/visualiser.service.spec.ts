import { TestBed } from '@angular/core/testing';

import { VisualiserService } from './visualiser.service';

describe('VisualiserService', () => {
  let service: VisualiserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VisualiserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
