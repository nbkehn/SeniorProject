import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../alert/alert.service';
import { FlooringService } from '../flooring/flooring.service';
import { Observable } from 'rxjs';
import { Flooring } from '../flooring/flooring';

@Component({
  selector: 'app-print-one-qr',
  templateUrl: './print-one-qr.component.html',
  styleUrls: ['./print-one-qr.component.less']
})
export class PrintOneQRComponent implements OnInit {

  floorId : number;
  flooring : Observable<Flooring>;
  bufferedImage : any;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService, private flooringService: FlooringService) { 
      this.route.queryParams.subscribe(params => {
      this.floorId = params['id'];
    });
    console.log(this.floorId);
    this.getQRCode(this.floorId);
  }

  ngOnInit(): void {
  }

  getQRCode(id: number) {
    this.flooringService.getFlooringQR(id).subscribe(data => {
      console.log(data);
    })
  }

}
