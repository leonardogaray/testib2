import { TestBed } from '@angular/core/testing';

import { StockOptionLoadService } from './stockoption-load.service';

describe('StockOptionLoadService', () => {
  let service: StockOptionLoadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockOptionLoadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
