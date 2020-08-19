import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { TokenStorageService } from './token-storage.service';

let API_URL = environment.apiUrl + '/stockoption';

@Injectable({
  providedIn: 'root'
})
export class StockOptionSimulationService {

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { 
    
  }

  getCSV(filename: string): Observable<any> {
    return this.http.get(API_URL + '/file/files/getCSV/' + filename + '/' + this.tokenStorageService.getUsername());
  }

  getStrategies(filename: string, userCash: number, buyPercentage: number, sellPercentage: number, buyAverageValue: number, sellDaysNumber: number): Observable<any> {
    return this.http.get(API_URL + '/simulate/' + this.tokenStorageService.getUsername() + '/' + filename + '/' + userCash + '/' + buyPercentage + '/' + sellPercentage + '/' + buyAverageValue + '/' + sellDaysNumber);
  }
}
