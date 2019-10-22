/**
 * Angular component for viewing floorings
 *
 * @package flooring
 * @author Noah Trimble
 * @modifiedBy Will Duke
 */
import { Observable } from "rxjs";
import { FlooringService as FlooringService } from "../flooring.service";
import { Flooring } from "../flooring";
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
  customers: Observable<Flooring[]>;

  /**
   * Constructor for the CustomerListComponent, doesn't really do anything right now
   * @param customerService the service for the customer component
   * @param router the router to route the customers to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private flooringService: FlooringService,
              private router: Router,
              private alertService: AlertService,
              private ngxSmartModalService: NgxSmartModalService) {}

  /**
   * reloads the data on initialize of the page to ensure that the page has the most updated details
   */
  ngOnInit() {
    this.reloadData();
  }

  /**
   * reloads the data with the most updated list of customers from the database
   * calls from the customer service so that it makes the DB call
   */
  reloadData() {
    this.customers = this.flooringService.getFlooringList();
  }

  /**
   * deletes the customer from the database and prints the response to the console
   * @param id the id of the customer to delete
   */
  deleteFlooring(id: number) {
    this.flooringService.deleteFlooring(id)
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
  translateCommunicationPreference(id: number) {
    let options = this.customerService.getCommunicationPreferenceOptions();
    let found = options.find(function(option) {
       return option.id === id;
    });

    if (typeof found !== 'undefined') {
      return found.name;
    }
    else {
      return '';
    }
  }
}
