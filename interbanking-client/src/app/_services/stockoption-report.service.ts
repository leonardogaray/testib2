import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

let API_URL = environment.apiUrl + '/stockoption';

@Injectable({
  providedIn: 'root'
})
export class StockOptionReportService {

  constructor(private http: HttpClient) { 
    
  }

  getCSV(filename: string): Observable<any> {
    return this.http.get(API_URL + '/file/files/getCSV/' + filename);
  }

  getStrategies(filename: string, userCash: number, buyPercentage: number, sellPercentage: number, buyAverageValue: number, sellDaysNumber: number): Observable<any> {
    return this.http.get(API_URL + '/simulate/' + filename + '/' + userCash + '/' + buyPercentage + '/' + sellPercentage + '/' + buyAverageValue + '/' + sellDaysNumber);
  }
}
