import { TestBed } from '@angular/core/testing';

import { IResolverService } from './i-resolver.service';

describe('IResolverService', () => {
  let service: IResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
