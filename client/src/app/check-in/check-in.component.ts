import { Component, OnInit } from '@angular/core';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { Flooring } from '../flooring/flooring';
import { FlooringService } from '../flooring/flooring.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer/customer.service';
import { AlertService } from '../alert/alert.service';
import {CommonModule} from '@angular/common';


@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.less']
})
export class CheckInComponent implements OnInit {

  private qrString: string;
  private hashCode: number;
  public flooringOptions: Flooring[];
  public scannedFlooring: Flooring[];
  retrievedSample: any;

  constructor(private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router,
    private alertService: AlertService, private flooringService: FlooringService) { }

  ngOnInit(): void {
  }

  onCodeResult(result: string) {
    this.qrString = result;
    this.hashCode = Number(this.qrString);
    console.log(this.qrString);
    //this.findFlooring();
  }

  findFlooring() {
    this.flooringService.getFlooringsList()
    .subscribe(
      data => {
        this.flooringOptions = data;
        this.flooringOptions.forEach(this.checkHash)
      },
      error => {
        this.alertService.error('Floorings could not be loaded.', false);
      });

  
  }

  checkHash(flooring: any) {
    if (this.hashCode == flooring.hashCode ) {
      this.flooringService.getFlooringbyHash(this.hashCode)
      .subscribe(
        data => {
          this.alertService.success('Sample successfuly scanned', true);
          this.retrievedSample = data;
          this.scannedFlooring.push(this.retrievedSample);
        },
        error => {
          this.alertService.error('Sample is not in database', false);
        }
      )
    }

  }

}
