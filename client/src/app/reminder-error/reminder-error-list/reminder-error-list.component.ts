/**
 * Angular component for viewing reminder errors
 *
 * @package reminder-error
 * @author Noah Trimble
 */
import { Observable } from "rxjs";
import { ReminderErrorService } from "../reminder-error.service";
import { ReminderError } from "../reminder-error";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-reminder-error-list",
  templateUrl: "./reminder-error-list.component.html"
})
export class ReminderErrorListComponent implements OnInit {
  // the list of reminder errors
  reminderErrors: Observable<ReminderError[]>;

  /**
   * Constructor for the ReminderErrorListComponent, doesn't really do anything right now
   * @param reminderErrorService the service for the reminder error component
   * @param router the router to route the reminders error to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private reminderErrorService: ReminderErrorService,
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
   * reloads the data with the most updated list of reminder errors from the database
   * calls from the reminder error service so that it makes the DB call
   */
  reloadData() {
    this.reminderErrors = this.reminderErrorService.getReminderErrorsList();
  }

  /**
   * Resend notifications
   */
  resendNotifications() {
    this.reminderErrorService.resend()
      .subscribe(
        data => {
          this.alertService.success('Notifications started sending.', false);
        },
        error => {
          this.alertService.error('Notifications could not be resent.', false);
        });
  }
}
