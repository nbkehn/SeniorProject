import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/customer/customer';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/customer/customer.service';
import { AlertService } from 'src/app/alert/alert.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-check-out-form',
  templateUrl: './check-out-form.component.html',
  styleUrls: ['./check-out-form.component.less']
})
export class CheckOutFormComponent implements OnInit {
   // the customer object to create and store data into
   customer: Customer = new Customer();

  constructor(private route: ActivatedRoute,
    private customerService: CustomerService,
    private router: Router,
    private alertService: AlertService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.save();
  }

  save() {
    // saves the customer object to the database -- if the customer hasn't been created before, it saves as a new entry
    // if the customer has been created before, it updates the customer
    let response = this.customerService.createCustomer(this.customer);
    response.subscribe(
      data => {
        
        // Display success message and go back to list
        this.alertService.success('Customer saved successfully.', true);
        this.router.navigate(['/flooring/check-out/scan/'], { queryParams: { id: data.id } });
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The customer could not be saved.', false);
      });
  }

}
