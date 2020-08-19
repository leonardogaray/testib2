import { TestBed } from '@angular/core/testing';

import { StockOptionSimulationService } from './stockoption-simulation.service';

describe('StockOptionLoadService', () => {
  let service: StockOptionSimulationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockOptionSimulationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
