import { TestBed } from '@angular/core/testing';

import { HttpHomeService } from './http-home.service';

describe('HttpHomeService', () => {
  let service: HttpHomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpHomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
