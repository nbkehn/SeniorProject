/**
 * Angular component for viewing customers
 *
 * @package customer
 * @author Noah Trimble
 * @modifiedBy Soumya Bagade
 */
import { Observable } from "rxjs";
import { CustomerService } from "../customer.service";
import { Customer } from "../customer";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-customer-list",
  templateUrl: "./customer-list.component.html"
})
export class CustomerListComponent implements OnInit {
  // the list of customers
  customers: Observable<Customer[]>;
  // Communication preferences
  preferences;

  /**
   * Constructor for the CustomerListComponent, doesn't really do anything right now
   * @param customerService the service for the customer component
   * @param router the router to route the customers to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private customerService: CustomerService,
              private router: Router,
              private alertService: AlertService,
              private ngxSmartModalService: NgxSmartModalService) {}

  /**
   * reloads the data on initialize of the page to ensure that the page has the most updated details
   */
  ngOnInit() {
    this.reloadData();
    this.preferences = [];
    this.setPreferences();
  }

  /**
   * reloads the data with the most updated list of customers from the database
   * calls from the customer service so that it makes the DB call
   */
  reloadData() {
    this.customers = this.customerService.getCustomersList();
  }

  /**
   * Set communication preferences
   */
  setPreferences() {
    this.customerService.getCommunicationPreferenceOptions()
      .subscribe(
        data => {
          this.preferences = data;
        },
        error => {
          this.alertService.error('Communication preferences could not be loaded.', false);
        });
  }

  /**
   * deletes the customer from the database and prints the response to the console
   * @param id the id of the customer to delete
   */
  deleteCustomer(id: number) {
    this.customerService.deleteCustomer(id)
      .subscribe(
        data => {
          this.alertService.success('Customer was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('Customer could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the customer to edit
   */
  editCustomer(id: number) {
    this.router.navigate(['/customer/edit', id]);
  }

  /**
   * Translate communication preference option id to value
   * @param id Option id
   * @return translated communication preference
   */
  translateCommunicationPreference(id: string) {
    return this.preferences[id];
  }
}
