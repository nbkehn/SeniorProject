/**
 * Angular component for creating and editing rsas
 *
 * @package rsa
 * @author Noah Trimble
 * @modifiedBy Soumya Bagade
 */
import { RsaService } from '../rsa.service';
import { Rsa } from '../rsa';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-rsa',
  templateUrl: './modify-rsa.component.html'
})
export class ModifyRsaComponent implements OnInit {
  // the rsa's ID in the database
  id: number;
  // the rsa object to create and store data into
  rsa: Rsa = new Rsa();
  // the title for the page
  title: string;

  /**
   * Creates the instance of the component
   * @param route
   * @param rsaService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private rsaService: RsaService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with rsa data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initializes a new rsa
    this.rsa = new Rsa();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the rsa has been stored in the database and is now being edited (Edit RSA) or created as a new one (Create RSA)
    this.title = this.id ? 'Edit RSA' : 'Create RSA';

    // if the id is not null, it means that the rsa has already been stored and is now being edited.
    // tries to get the rsa from the database and logs whether the rsa could be retrieved from the database in the console
    if (this.id) {
      this.rsaService.getRSA(this.id)
        .subscribe(data => {
          this.rsa = data;
        },
            error => {
          this.alertService.error('RSA could not be loaded.', false);
        });
    }
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the rsa object to the database -- if the rsa hasn't been created before, it saves as a new entry
    // if the rsa has been created before, it updates the rsa
    let response = !this.id ? this.rsaService.createRSA(this.rsa)
      : this.rsaService.updateRSA(this.id, this.rsa);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('RSA saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The RSA could not be saved.', false);
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
   * Resets the page back to the rsas list instead of the add rsa page
   */
  gotoList() {
    this.router.navigate(['/rsa/index']);
  }
}
