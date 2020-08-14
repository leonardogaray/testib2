import { Component, OnInit } from '@angular/core';
import { CommonsHelper } from '../../_helpers/commons.helper';
import { StockOptionReportService } from '../../_services/stockoption-report.service';
import { ActivatedRoute } from '@angular/router';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { StrategyReport } from '../../models/strategy-report';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stock-option-report',
  templateUrl: './stockoption-report.component.html',
  styleUrls: ['./stockoption-report.component.css', '../../app.component.css']
})
export class StockOptionReportComponent implements OnInit {
  //NG2-CHARTS
  //[{ data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' }]
  public lineChartData: ChartDataSets[] = [];
  //['January', 'February', 'March', 'April', 'May', 'June', 'July']
  public lineChartLabels: Label[] = [];
  public lineChartOptions: ChartOptions = {
    responsive: true,
  };
  public lineChartColors: Color[] = [];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [];

  userCash: number = 100000;
  fileName: string = "";

  //STRATEGIES
  public strategyReports: StrategyReport[] = [];
  constructor(
    private stockOptionReportService: StockOptionReportService, 
    private route: ActivatedRoute, 
    private router: Router,
    private commonsHelper: CommonsHelper) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.has('filename')){
        this.fileName = params.get('filename');
        this.stockOptionReportService.getCSV(this.fileName).subscribe((data: any) => {
          this.transformData(data);
        });
      }
    });
    
  }

  simulate(): void{
    this.stockOptionReportService.getStrategies(this.fileName, this.userCash).subscribe((data: any) => {
      data.forEach(strategy => {
        this.strategyReports.push(this.calculateStrategyReport(strategy));
      })
    });
  }

  private calculateStrategyReport(strategy: any): StrategyReport {
    let strategyReport: StrategyReport = new StrategyReport();
    let top5Purchases : any[] = [];
    let top3PurchasesProfitable : any[] = [];

    strategy.stockOptionStrategies.forEach(stockOptionStrategy => {
      let diff = stockOptionStrategy.sellPrice - stockOptionStrategy.buyPrice;

      //Earn Money
      strategyReport.earnMoney += diff;
      
      //Total Purchases
      strategyReport.totalPurchases++;
      
      //Total Purchases Profitable
      if(diff > 0)
        strategyReport.totalPurchasesProfitable++;

      //Top 5 Purchases
      if(!top5Purchases[stockOptionStrategy.brand]){
        top5Purchases[stockOptionStrategy.brand] = 0;
      }
      top5Purchases[stockOptionStrategy.brand]++;

      //Top 3 Purchases Profitable
      if(!top3PurchasesProfitable[stockOptionStrategy.brand]){
        top3PurchasesProfitable[stockOptionStrategy.brand] = 0;
      }
      top3PurchasesProfitable[stockOptionStrategy.brand] += diff;
    });

    strategyReport.top5Purchases = this.commonsHelper.sortAssocArray(top5Purchases);
    strategyReport.top3PurchasesProfitable = this.commonsHelper.sortAssocArray(top3PurchasesProfitable);
    strategyReport.strategyId = strategy.strategyId;
    
    return strategyReport;
  }

  private transformData(data: any): void {
    let temp: any[] = [];
    let final: any[] = [];

    //Group elements by brand
    data.forEach(element => {
      if(!temp[element.brand]){
        temp[element.brand] = [];
      }
      temp[element.brand].push(element);
    });
    
    //Generate ng2-charts structure
    let index = 0;
    for(var key in temp){
      let serie: any = {};
      serie["label"] = key;
      serie["data"] = [];
      temp[key].forEach(obj =>{
        serie["data"].push(obj.price);
        if(index === 0)
          this.lineChartLabels.push(obj.date);
      });
      final.push(serie);
      index++;
    };

    this.lineChartData = final;
  }

  back(){
    this.router.navigate(['/stock-option-load']);
  }
}
