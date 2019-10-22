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
  selector: "app-flooring-list",
  templateUrl: "./flooring-list.component.html"
})
export class FlooringListComponent implements OnInit {
  // the list of floorings
  floorings: Observable<Flooring[]>;

  /**
   * Constructor for the FlooringListComponent, doesn't really do anything right now
   * @param flooringService the service for the flooring component
   * @param router the router to route the floorings to each page
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
   * reloads the data with the most updated list of floorings from the database
   * calls from the flooring service so that it makes the DB call
   */
  reloadData() {
    this.floorings = this.flooringService.getFlooringsList();
  }

  /**
   * deletes the flooring from the database and prints the response to the console
   * @param id the id of the flooring to delete
   */
  deleteFlooring(id: number) {
    this.flooringService.deleteFlooring(id)
      .subscribe(
        data => {
          this.alertService.success('Flooring was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('Flooring could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the flooring to edit
   */
  editFlooring(id: number) {
    this.router.navigate(['/flooring/edit', id]);
  }
}
