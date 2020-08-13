import { TestBed } from '@angular/core/testing';

import { StockOptionReportService } from './stockoption-report.service';

describe('StockOptionLoadService', () => {
  let service: StockOptionReportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockOptionReportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
