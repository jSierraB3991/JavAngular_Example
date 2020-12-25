import { TestBed } from '@angular/core/testing';

import { ProGuardService } from './pro-guard.service';

describe('ProGuardService', () => {
  let service: ProGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
