import { TestBed } from '@angular/core/testing';

import { ReqApiService } from './req-api.service';

describe('ReqApiService', () => {
  let service: ReqApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReqApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
