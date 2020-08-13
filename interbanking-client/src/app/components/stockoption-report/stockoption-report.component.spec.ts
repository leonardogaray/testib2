import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockOptionReportComponent } from './stockoption-report.component';

describe('StockOptionReportComponent', () => {
  let component: StockOptionReportComponent;
  let fixture: ComponentFixture<StockOptionReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockOptionReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockOptionReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
