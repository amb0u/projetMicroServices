import { TestBed } from '@angular/core/testing';

import { InfosTransfrService } from './infos-transfr.service';

describe('InfosTransfrService', () => {
  let service: InfosTransfrService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InfosTransfrService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
