/**
 * Angular component for viewing rsas
 *
 * @package rsa
 * @author Noah Trimble
 * @modifiedBy Soumya Bagade
 */
import { Observable } from "rxjs";
import { RsaService } from "../rsa.service";
import { Rsa } from "../rsa";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-rsa-list",
  templateUrl: "./rsa-list.component.html"
})
export class RsaListComponent implements OnInit {
  // the list of rsas
  rsas: Observable<Rsa[]>;

  /**
   * Constructor for the RSAListComponent, doesn't really do anything right now
   * @param rsaService the service for the rsa component
   * @param router the router to route the rsas to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private rsaService: RsaService,
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
   * reloads the data with the most updated list of rsas from the database
   * calls from the rsa service so that it makes the DB call
   */
  reloadData() {
    this.rsas = this.rsaService.getRSAsList();
  }

  /**
   * deletes the rsa from the database and prints the response to the console
   * @param id the id of the rsa to delete
   */
  deleteRSA(id: number) {
    this.rsaService.deleteRSA(id)
      .subscribe(
        data => {
          this.alertService.success('RSA was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('RSA could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the rsa to edit
   */
  editRSA(id: number) {
    this.router.navigate(['/rsa/edit', id]);
  }
}
