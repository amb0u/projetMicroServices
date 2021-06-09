import { TestBed } from '@angular/core/testing';

import { TaskSubmitService } from './task-submit.service';

describe('TaskSubmitService', () => {
  let service: TaskSubmitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskSubmitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
