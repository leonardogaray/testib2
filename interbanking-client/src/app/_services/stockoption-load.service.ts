import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FileInfo } from '../models/file-info';
import { environment } from '../../environments/environment';

let API_URL = environment.apiUrl + '/stockoption/file';

@Injectable({
  providedIn: 'root'
})
export class StockOptionLoadService {

  constructor(private http: HttpClient) { 

  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', API_URL + '/upload', formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(API_URL + '/files/');
  }

  process(fileInfo: FileInfo): Observable<any> {
    return this.http.get(API_URL + '/simulate/' + fileInfo.name);
  }
}
