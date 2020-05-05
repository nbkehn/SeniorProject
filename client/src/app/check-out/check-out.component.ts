import { Component, OnInit } from '@angular/core';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { Flooring } from '../flooring/flooring';
import { FlooringService } from '../flooring/flooring.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer/customer.service';
import { AlertService } from '../alert/alert.service';
import { Customer } from '../customer/customer';


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
      this.scannedFlooring.push(retrievedSample);
    }

    deleteFlooring(sampleId: number) {
      this.scannedFlooring = this.scannedFlooring.filter(({ id }) => id !== sampleId); 
    }

    checkOut(flooring : Flooring, sampleid: number) {
      flooring.sampleChecked = true;
      flooring.checkedTo = this.custId;
      this.flooringService.updateFlooring(flooring.id, flooring)
      .subscribe(
        data => {
          // Display success message and go back to list
          console.log(data);
          this.alertService.success('Sample successfully checked out.', true);
          this.scannedFlooring = this.scannedFlooring.filter(({ id }) => id !== sampleid); 
        },
        error => {
          // Display error message on error and remain in form
          this.alertService.error('The sample could not be checked out.', false);
        });
    }

    finish() {
      this.router.navigate(["checkout-landing"]);
    }
  }
