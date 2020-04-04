import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-sample',
  templateUrl: './upload-sample.component.html',
  styleUrls: ['./upload-sample.component.less']
})
export class UploadSampleComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

 fileSelect(event) {
    var files = event.target.files;
    var file = files[0];
    var read = new FileReader();
    read.readAsText(file);
  
 }

}
