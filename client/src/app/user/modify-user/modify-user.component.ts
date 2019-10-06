/**
 * Angular component for creating and editing users
 *
 * @package user
 * @author Noah Trimble
 * @modifiedBy Soumya Bagade
 */
import { UserService } from '../user.service';
import { User } from '../user';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-user',
  templateUrl: './modify-user.component.html'
})
export class ModifyUserComponent implements OnInit {
  // the user's ID in the database
  id: number;
  // the user object to create and store data into
  user: User = new User();
  // the title for the page
  title: string;

  /**
   * Creates the instance of the component
   * @param route
   * @param userService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with user data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initializes a new user
    this.user = new User();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the user has been stored in the database and is now being edited (Edit User) or created as a new one (Create User)
    this.title = this.id ? 'Edit User' : 'Create User';

    // if the id is not null, it means that the user has already been stored and is now being edited.
    // tries to get the user from the database and logs whether the user could be retrieved from the database in the console
    if (this.id) {
      this.userService.getUser(this.id)
        .subscribe(data => {
          this.user = data;
        },
            error => {
          this.alertService.error('User could not be loaded.', false);
        });
    }
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the user object to the database -- if the user hasn't been created before, it saves as a new entry
    // if the user has been created before, it updates the user
    let response = !this.id ? this.userService.createUser(this.user)
      : this.userService.updateUser(this.id, this.user);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('User saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The user could not be saved.', false);
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
   * Resets the page back to the users list instead of the add user page
   */
  gotoList() {
    this.router.navigate(['/user/index']);
  }
}
