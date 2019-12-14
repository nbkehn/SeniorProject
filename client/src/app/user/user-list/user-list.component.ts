/**
 * Angular component for viewing users
 *
 * @package user
 * @author Noah Trimble and Soumya Bagade
 */
import { Observable } from "rxjs";
import { UserService } from "../user.service";
import { User } from "../user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html"
})
export class UserListComponent implements OnInit {
  // the list of users
  users: Observable<User[]>;

  /**
   * Constructor for the UserListComponent, doesn't really do anything right now
   * @param userService the service for the user component
   * @param router the router to route the users to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private userService: UserService,
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
   * reloads the data with the most updated list of users from the database
   * calls from the user service so that it makes the DB call
   */
  reloadData() {
    this.users = this.userService.getUsersList();
  }

  /**
   * deletes the user from the database and prints the response to the console
   * @param id the id of the user to delete
   */
  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          this.alertService.success('User was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('User could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the user to edit
   */
  editUser(id: number) {
    this.router.navigate(['/user/edit', id]);
  }
}
