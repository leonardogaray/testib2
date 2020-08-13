import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockOptionLoadComponent } from './stockoption-load.component';

describe('StockOptionLoadComponent', () => {
  let component: StockOptionLoadComponent;
  let fixture: ComponentFixture<StockOptionLoadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockOptionLoadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockOptionLoadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
