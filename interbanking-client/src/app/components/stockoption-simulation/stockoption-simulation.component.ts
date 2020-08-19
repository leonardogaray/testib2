import { Component, OnInit } from '@angular/core';
import { CommonsHelper } from '../../_helpers/commons.helper';
import { StockOptionSimulationService } from '../../_services/stockoption-simulation.service';
import { ActivatedRoute } from '@angular/router';
import { StrategySimulation } from '../../models/strategy-simulation';
import { Router } from '@angular/router';
import { StockOptionChartComponent } from '../stockoption-chart/stockoption-chart.component';

@Component({
  selector: 'app-stock-option-simulation',
  templateUrl: './stockoption-simulation.component.html',
  styleUrls: ['./stockoption-simulation.component.css', '../../app.component.css']
})
export class StockOptionSimulationComponent implements OnInit {
  userCash: number = 100000;
  fileName: string = "";
  buyPercentage: number = 1;
  sellPercentage: number = 2;
  buyAverageValue: number = 1;
  sellDaysNumber: number = 5;
  active = 1;

  //STRATEGIES
  public strategySimulations: StrategySimulation[] = [];

  constructor(
    private stockOptionSimulationService: StockOptionSimulationService, 
    private route: ActivatedRoute, 
    private router: Router,
    private commonsHelper: CommonsHelper) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.has('filename')){
        this.fileName = params.get('filename');
      }
    });
    
  }

  simulate(): void{
    this.stockOptionSimulationService.getStrategies(this.fileName, this.userCash, this.buyPercentage, this.sellPercentage, this.buyAverageValue, this.sellDaysNumber).subscribe((data: any) => {
      this.strategySimulations = [];
      data.forEach(strategy => {
        this.strategySimulations.push(this.calculateStrategyReport(strategy));
      });
      this.active = 2;
    });
  }

  private calculateStrategyReport(strategy: any): StrategySimulation {
    let strategySimulation: StrategySimulation = new StrategySimulation();
    let top5Purchases : any[] = [];
    let top3PurchasesProfitable : any[] = [];

    strategy.stockOptionStrategies.forEach(stockOptionStrategy => {
      let diff = stockOptionStrategy.sellPrice - stockOptionStrategy.buyPrice;

      //Earn Money
      strategySimulation.earnMoney += diff;
      
      //Total Purchases
      strategySimulation.totalPurchases++;
      
      //Total Purchases Profitable
      if(diff > 0)
        strategySimulation.totalPurchasesProfitable++;

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

    strategySimulation.top5Purchases = this.commonsHelper.sortAssocArray(top5Purchases);
    strategySimulation.top3PurchasesProfitable = this.commonsHelper.sortAssocArray(top3PurchasesProfitable);
    strategySimulation.strategyId = strategy.strategyId;
    
    return strategySimulation;
  }

  back(){
    this.router.navigate(['/stock-option-load']);
  }
}
