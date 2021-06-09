import { TestBed } from '@angular/core/testing';

import { OprationServiceService } from './opration-service.service';

describe('OprationServiceService', () => {
  let service: OprationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OprationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
