import { Component, OnInit } from '@angular/core';
import { FlooringService } from '../flooring.service';
import { AlertService } from 'src/app/alert/alert.service';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { Router } from '@angular/router';
import { FlexibleConnectedPositionStrategy } from '@angular/cdk/overlay';
import { Flooring } from '../flooring';

@Component({
  selector: 'app-upload-sample',
  templateUrl: './upload-sample.component.html',
  styleUrls: ['./upload-sample.component.less']
})
export class UploadSampleComponent implements OnInit {
  flooring: Flooring = new Flooring();

  fileToSend: File;
  //fileData : [];
  floor : Flooring;
  event : any;
  
  
  /**
    * Constructor for the FlooringListComponent, doesn't really do anything right now
    * @param flooringService the service for the flooring component
    * @param router the router to route the floorings to each page
    * @param alertService serivce used to add alert messages
    * @param ngxSmartModalService service used to create modals
    */
  constructor(private flooringService: FlooringService,
    private router: Router,
    private alertService: AlertService,
    private ngxSmartModalService: NgxSmartModalService, ) { }

  ngOnInit(): void {
  };

  fileSelect = function (event) {
    this.event = event;
    var files = event.target.files;
    this.fileToSend = files[0];
    // var reader = new FileReader();
    // reader.readAsText(this.fileToSend);
    // reader.onload = this.handleLoad;
  }

  // handleLoad(event) {
  //   let file = event.target.result;
  //   console.table(event.target.result);
    
  //   var floors = [];
  //   var details = [];
  //   let rows = file.split("\r\n");
  //   for (var i = 0; i < rows.length; i++) {
  //     let row = rows[i].split(",");
  //     var row_col = [];
  //     for (var j = 0; j < row.length; j++) {
  //       row_col.push(row[j]);
  //     }
  //     floors.push(row_col);
  //   }
  //   this.flooring.style = floors[1][1];
  //   for (var i = 1; i < floors.length; i++) {
  //     this.fileData = (floors[i]);
  //   }
  // }
    

  

  onSubmit() {
    const uploadSampleFile = new FormData();
    uploadSampleFile.append('file', this.fileToSend, this.fileToSend.name)
    let response = this.flooringService.uploadSampleFile(uploadSampleFile);
    response.subscribe(data => {
      // Display success message and go back to list
      this.alertService.success('Samples saved successfully.', true);
      this.gotoList();
    },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('Samples could not be saved.', false);
      });
  }

  // save() {
  //   let response = this.flooringService.uploadSampleFile();
  //   response.subscribe(data => {
  //     // Display success message and go back to list
  //     this.alertService.success('Samples saved successfully.', true);
  //     this.gotoList();
  //   },
  //     error => {
  //       // Display error message on error and remain in form
  //       this.alertService.error('Samples could not be saved.', false);
  //     });
  // }

  /**
    * Resets the page back to the floorings list instead of the add flooring page
    */
  gotoList() {
    this.router.navigate(['/flooring/index']);
  }

}
