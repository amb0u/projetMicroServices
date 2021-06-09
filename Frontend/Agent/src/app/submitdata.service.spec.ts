import { TestBed } from '@angular/core/testing';

import { SubmitdataService } from './submitdata.service';

describe('SubmitdataService', () => {
  let service: SubmitdataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubmitdataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
