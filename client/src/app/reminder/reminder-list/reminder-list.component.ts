/**
 * Angular component for viewing reminders
 *
 * @package reminder
 * @author Noah Trimble
 */
import { Observable } from "rxjs";
import { ReminderService } from "../reminder.service";
import { Reminder } from "../reminder";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { AlertService } from "../../alert/alert.service";
import { NgxSmartModalService } from "ngx-smart-modal";

@Component({
  selector: "app-reminder-list",
  templateUrl: "./reminder-list.component.html"
})
export class ReminderListComponent implements OnInit {
  // the list of reminders
  reminders: Observable<Reminder[]>;

  /**
   * Constructor for the ReminderListComponent, doesn't really do anything right now
   * @param reminderService the service for the reminder component
   * @param router the router to route the reminders to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private reminderService: ReminderService,
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
   * reloads the data with the most updated list of reminders from the database
   * calls from the reminder service so that it makes the DB call
   */
  reloadData() {
    this.reminders = this.reminderService.getRemindersList();
  }

  /**
   * deletes the reminder from the database and prints the response to the console
   * @param id the id of the reminder to delete
   */
  deleteReminder(id: number) {
    this.reminderService.deleteReminder(id)
      .subscribe(
        data => {
          this.alertService.success('Reminder was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('Reminder could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the reminder to edit
   */
  editReminder(id: number) {
    this.router.navigate(['/reminder/edit', id]);
  }
}
