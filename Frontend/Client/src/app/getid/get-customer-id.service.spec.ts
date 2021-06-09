import { TestBed } from '@angular/core/testing';

import { GetCustomerIdService } from './get-customer-id.service';

describe('GetCustomerIdService', () => {
  let service: GetCustomerIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetCustomerIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
