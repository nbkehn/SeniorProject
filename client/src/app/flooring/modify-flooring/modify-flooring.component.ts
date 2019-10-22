/**
 * Angular component for creating and editing customers
 *
 * @package customer
 * @author Noah Trimble
 * @modifiedBy Soumya Bagade
 */
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-customer',
  templateUrl: './modify-customer.component.html'
})
export class ModifyCustomerComponent implements OnInit {
  // the customer's ID in the database
  id: number;
  // the customer object to create and store data into
  customer: Customer = new Customer();
  // the title for the page
  title: string;

  /**
   * Creates the instance of the component
   * @param route
   * @param customerService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with customer data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initializes a new customer
    this.customer = new Customer();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the customer has been stored in the database and is now being edited (Edit Customer) or created as a new one (Create Customer)
    this.title = this.id ? 'Edit Customer' : 'Create Customer';

    // if the id is not null, it means that the customer has already been stored and is now being edited.
    // tries to get the customer from the database and logs whether the customer could be retrieved from the database in the console
    if (this.id) {
      this.customerService.getCustomer(this.id)
        .subscribe(data => {
          this.customer = data;
        },
            error => {
          this.alertService.error('Customer could not be loaded.', false);
        });
    }
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the customer object to the database -- if the customer hasn't been created before, it saves as a new entry
    // if the customer has been created before, it updates the customer
    let response = !this.id ? this.customerService.createCustomer(this.customer)
      : this.customerService.updateCustomer(this.id, this.customer);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Customer saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The customer could not be saved.', false);
      });
  }

  /**
   * This method is called on submit.
   * Calls on the save method to save the entry to the database.
   */
  onSubmit() {
    this.save();
  }

  /**
   * Resets the page back to the customers list instead of the add customer page
   */
  gotoList() {
    this.router.navigate(['/customer/index']);
  }

  /**
   * Get communication preference options
   * @return communication preference options
   */
  getCommunicationPreferenceOptions() {
    return this.customerService.getCommunicationPreferenceOptions();
  }
}
