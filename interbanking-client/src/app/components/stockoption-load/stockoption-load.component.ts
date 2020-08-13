import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockOptionLoadService } from '../../_services/stockoption-load.service';
import { FileInfo } from '../../models/file-info';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stock-option-load',
  templateUrl: './stockoption-load.component.html',
  styleUrls: ['./stockoption-load.component.css']
})
export class StockOptionLoadComponent implements OnInit {

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileInfos: Observable<any>;

  constructor(private stockOptionLoadService: StockOptionLoadService, private router: Router) { }

  ngOnInit(): void {
    this.fileInfos = this.stockOptionLoadService.getFiles();
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;
  
    this.currentFile = this.selectedFiles.item(0);
    this.stockOptionLoadService.upload(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.stockOptionLoadService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });
  
    this.selectedFiles = undefined;
  }

  report(file: FileInfo){
    this.router.navigate(['/stock-option-report/' + file.name]);
  }

}
