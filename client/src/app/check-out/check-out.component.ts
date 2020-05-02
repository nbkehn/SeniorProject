import { Component, OnInit } from '@angular/core';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { Flooring } from '../flooring/flooring';
import { FlooringService } from '../flooring/flooring.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer/customer.service';
import { AlertService } from '../alert/alert.service';
import { Customer } from '../customer/customer';
import { NgxSmartModalService } from "ngx-smart-modal";


@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.less']
})
export class CheckOutComponent implements OnInit {

  private qrString: string;
  private hashCode: string;
  public flooringOptions: Flooring[];
  public scannedFlooring: any = [];
  retrievedSample: Flooring;
  custId : number;
  retrievedCustomer: any;

  constructor(private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router,
    private alertService: AlertService, private flooringService: FlooringService) { 
      this.route.queryParams.subscribe(params => {
      this.custId = params['id'];

    });
    console.log(this.custId);
  }

  ngOnInit(): void {
    
  }

  onCodeResult(result: string) {
    this.qrString = result;
    this.hashCode = this.qrString;
    console.log(this.hashCode);
    this.flooringService.getFlooringbyHash(this.hashCode)
      .subscribe(
        data => {
          this.alertService.success('Sample successfuly scanned', true);
          this.retrievedSample = data;
          this.pushSample(this.retrievedSample);
        },
        error => {
          this.alertService.error('Sample is not in database', false);
        }
      )
    }

    pushSample(retrievedSample: Flooring) {
      console.log(retrievedSample);
      this.scannedFlooring.push(retrievedSample);
    }

    deleteFlooring(sampleId: number) {
      console.log(this.scannedFlooring);
      this.scannedFlooring = this.scannedFlooring.filter(({ id }) => id !== sampleId); 
    }

    checkOut(flooring : Flooring) {
      flooring.checkedOut = true;
      this.retrievedCustomer = this.customerService.getCustomer(this.custId);
      console.log(this.retrievedCustomer);
      this.flooringService.updateFlooring(flooring.id, flooring)
      .subscribe(
        data => {
          // Display success message and go back to list
          this.alertService.success('Flooring saved successfully.', true);
        },
        error => {
          // Display error message on error and remain in form
          this.alertService.error('The flooring could not be saved.', false);
        });
    }
  }
