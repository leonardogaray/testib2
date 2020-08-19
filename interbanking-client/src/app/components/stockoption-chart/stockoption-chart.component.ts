import { Component, OnInit } from '@angular/core';
import { StockOptionSimulationService } from '../../_services/stockoption-simulation.service';
import { ActivatedRoute } from '@angular/router';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { Router } from '@angular/router';

@Component({
  selector: 'stock-option-chart',
  templateUrl: './stockoption-chart.component.html',
  styleUrls: ['./stockoption-chart.component.css', '../../app.component.css']
})
export class StockOptionChartComponent implements OnInit {
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

  fileName: string = "";

  constructor(
    private stockOptionSimulationService: StockOptionSimulationService, 
    private route: ActivatedRoute, 
    private router: Router) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if(params.has('filename')){
        this.fileName = params.get('filename');
        this.stockOptionSimulationService.getCSV(this.fileName).subscribe((data: any) => {
          this.transformData(data);
        });
      }
    });
    
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
}
