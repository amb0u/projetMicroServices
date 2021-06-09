import { TestBed } from '@angular/core/testing';

import { GetOfficerIdService } from './get-officer-id.service';

describe('GetOfficerIdService', () => {
  let service: GetOfficerIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetOfficerIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
