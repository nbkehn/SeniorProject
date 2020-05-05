import { Component, OnInit } from '@angular/core';
import { AlertService } from '../alert/alert.service';
import { FlooringService } from '../flooring/flooring.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer/customer.service';
import { Flooring } from '../flooring/flooring';
import { getQueryPredicate } from '@angular/compiler/src/render3/view/util';

@Component({
  selector: 'app-print-all-qr',
  templateUrl: './print-all-qr.component.html',
  styleUrls: ['./print-all-qr.component.less']
})
export class PrintAllQRComponent implements OnInit {

  flooringList: Flooring[];
  imgList: any = [];

  constructor(private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router,
    private alertService: AlertService, private flooringService: FlooringService) {
    this.flooringService.getFlooringsList().subscribe(data => {
      this.flooringList = data;
      for (let i = 0; i < this.flooringList.length; i++) {
        this.getQRString(this.flooringList[i]);
      }
    });

  }


  ngOnInit(): void {
  }

  getQRString(flooring: Flooring) {
    let sampleData = {
      style: flooring.style, color: flooring.color, qrCode: ""}
    this.flooringService.getFlooringQR(flooring.id).subscribe(data => {
      var base64String = btoa(String.fromCharCode.apply(null, new Uint8Array(data)));
      sampleData.qrCode = base64String;
    })
    this.imgList.push(sampleData);
  }



}
