/**
 * Angular component for creating and editing reminders
 *
 * @package reminder
 * @author Noah Trimble
 */
import { ReminderService } from '../reminder.service';
import { Reminder } from '../reminder';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-reminder',
  templateUrl: './modify-reminder.component.html'
})
export class ModifyReminderComponent implements OnInit {
  // the reminder's ID in the database
  id: number;
  // the reminder object to create and store data into
  reminder: Reminder = new Reminder();
  // the title for the page
  title: string;
  // object keys
  objectKeys;
  // Times to send
  timesToSend;
  // Templates
  templates;

  /**
   * Creates the instance of the component
   * @param route
   * @param reminderService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private reminderService: ReminderService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with reminder data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initialize mapped options
    this.timesToSend = [];
    this.templates = [];
    this.objectKeys = Object.keys;
    this.setTimesToSend();
    this.setTemplates();

    // initializes a new reminder
    this.reminder = new Reminder();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the reminder has been stored in the database and is now being edited (Edit Reminder) or created as a new one (Create Reminder)
    this.title = this.id ? 'Edit Reminder' : 'Create Reminder';

    // if the id is not null, it means that the reminder has already been stored and is now being edited.
    // tries to get the reminder from the database and logs whether the reminder could be retrieved from the database in the console
    if (this.id) {
      this.reminderService.getReminder(this.id)
        .subscribe(data => {
          this.reminder = data;
        },
            error => {
          this.alertService.error('Reminder could not be loaded.', false);
        });
    }
  }

  /**
   * Set times to send
   */
  setTimesToSend() {
    this.reminderService.getTimesToSend()
      .subscribe(
        data => {
          this.timesToSend = data;
        },
        error => {
          this.alertService.error('Times to send could not be loaded.', false);
        });
  }

  /**
   * Set templates
   */
  setTemplates() {
    this.reminderService.getTemplates()
      .subscribe(
        data => {
          this.templates = data;
        },
        error => {
          this.alertService.error('Templates could not be loaded.', false);
        });
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the reminder object to the database -- if the reminder hasn't been created before, it saves as a new entry
    // if the reminder has been created before, it updates the reminder
    let response = !this.id ? this.reminderService.createReminder(this.reminder)
      : this.reminderService.updateReminder(this.id, this.reminder);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Reminder saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The reminder could not be saved. Please note that time to send is unique per reminder.', false);
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
   * Resets the page back to the reminders list instead of the add reminder page
   */
  gotoList() {
    this.router.navigate(['/reminder/index']);
  }
}
