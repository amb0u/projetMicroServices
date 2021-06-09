import { TestBed } from '@angular/core/testing';

import { ApiAccountService } from './api-account.service';

describe('ApiAccountService', () => {
  let service: ApiAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
