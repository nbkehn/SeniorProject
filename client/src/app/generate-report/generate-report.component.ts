import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer/customer.service';
import { FlooringService } from '../flooring/flooring.service';
import { Flooring } from '../flooring/flooring';
import { Customer } from '../customer/customer';

@Component({
  selector: 'app-generate-report',
  templateUrl: './generate-report.component.html',
  styleUrls: ['./generate-report.component.less']
})
export class GenerateReportComponent implements OnInit {
  reports: any = []
  floorings: Flooring[];
  floor: Flooring;
  customer: Customer;

  constructor(private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router, private flooringService: FlooringService) {
    this.flooringService.getFlooringsList().subscribe(data => {
      this.floorings = data;
      for (let i = 0; i < this.floorings.length; i++) {
        this.floor = this.floorings[i];
        if (this.floor.sampleChecked == true) {
          let report = {
            type: "", style: "", color: "", company: "",
            name: "", phone: "",
            email: "",
          }
          this.customerService.getCustomer(this.floor.checkedTo).subscribe(data => {
            this.customer = data;
            report.name = this.customer.firstName + " " + this.customer.lastName;
            report.phone = this.customer.phone;
            report.email = this.customer.email;
          });
          report.type = this.floor.name;
          report.style = this.floor.style;
          report.color = this.floor.color;
          report.company = this.floor.company;
          this.reports.push(report);
        }
      }
    })

  }

  ngOnInit(): void {
  }

}
