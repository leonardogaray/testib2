import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockOptionSimulationComponent } from './stockoption-simulation.component';

describe('StockOptionReportComponent', () => {
  let component: StockOptionSimulationComponent;
  let fixture: ComponentFixture<StockOptionSimulationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockOptionSimulationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockOptionSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
