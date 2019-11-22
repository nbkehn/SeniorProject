/**
 * Angular component for viewing technicians
 *
 * @package technician
 * @author Noah Trimble and Soumya Bagade
 */
import { Observable } from "rxjs";
import { TechnicianService } from "../technician.service";
import { Technician } from "../technician";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-technician-list",
  templateUrl: "./technician-list.component.html"
})
export class TechnicianListComponent implements OnInit {
  // the list of technicians
  technicians: Observable<Technician[]>;

  /**
   * Constructor for the TechnicianListComponent, doesn't really do anything right now
   * @param technicianService the service for the technician component
   * @param router the router to route the technicians to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private technicianService: TechnicianService,
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
   * reloads the data with the most updated list of technicians from the database
   * calls from the technician service so that it makes the DB call
   */
  reloadData() {
    this.technicians = this.technicianService.getTechniciansList();
  }

  /**
   * deletes the technician from the database and prints the response to the console
   * @param id the id of the technician to delete
   */
  deleteTechnician(id: number) {
    this.technicianService.deleteTechnician(id)
      .subscribe(
        data => {
          this.alertService.success('Technician was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('Technician could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the technician to edit
   */
  editTechnician(id: number) {
    this.router.navigate(['/technician/edit', id]);
  }
}
