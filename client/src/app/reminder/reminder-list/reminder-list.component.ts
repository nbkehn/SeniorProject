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
import { TimeToSend } from '../timetosend';
import { UserType } from '../usertype';


@Component({
  selector: "app-reminder-list",
  templateUrl: "./reminder-list.component.html"
})
export class ReminderListComponent implements OnInit {
  // the list of reminders
  reminders: Observable<Reminder[]>;

   // Times to send
  timeToSendOptions: TimeToSend[];

  // user type options
  userTypeOptions: UserType[];



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
	this.timeToSendOptions = [];
	this.userTypeOptions = [];
    this.setTimesToSend();
    this.setUserTypes();
  }

  /**
   * reloads the data with the most updated list of reminders from the database
   * calls from the reminder service so that it makes the DB call
   */
  reloadData() {
    this.reminders = this.reminderService.getRemindersList();
  }

  /**
   * Translate time to send option id to value
   * @param id Option id
   * @return translated time to send
   */
  translateUserType(id: string) {
	for (let option of this.userTypeOptions) {
		if (option.id == id) {
			return option.name;
		}
	}
  }

  /**
   * Translate time to send option id to value
   * @param id Option id
   * @return translated time to send
   */
  translateTimeToSend(offset: number) {
	for (let option of this.timeToSendOptions) {
		if (option.offset == offset) {
			return option.name;
		}
	}
  }

  /**
   * Set times to send
   */
  setTimesToSend() {
    this.reminderService.getTimesToSend()
      .subscribe(
        data => {
          this.timeToSendOptions = data;
        },
        error => {
          this.alertService.error('Times to send could not be loaded.', false);
        });
  }

  /**
   * Set user types
   */
  setUserTypes() {
    this.reminderService.getUserTypes()
      .subscribe(
        data => {
          this.userTypeOptions = data;
        },
        error => {
          this.alertService.error('User types could not be loaded.', false);
        });
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
