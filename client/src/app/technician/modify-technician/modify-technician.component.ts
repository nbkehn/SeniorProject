/**
 * Angular component for creating and editing technicians
 *
 * @package technician
 * @author Noah Trimble and Soumya Bagade
 */
import { TechnicianService } from '../technician.service';
import { Technician } from '../technician';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-technician',
  templateUrl: './modify-technician.component.html'
})
export class ModifyTechnicianComponent implements OnInit {
  // the technician's ID in the database
  id: number;
  // the technician object to create and store data into
  technician: Technician = new Technician();
  // the title for the page
  title: string;

  /**
   * Creates the instance of the component
   * @param route
   * @param technicianService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private technicianService: TechnicianService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with technician data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initializes a new technician
    this.technician = new Technician();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the technician has been stored in the database and is now being edited (Edit Technician) or created as a new one (Create Technician)
    this.title = this.id ? 'Edit Technician' : 'Create Technician';

    // if the id is not null, it means that the technician has already been stored and is now being edited.
    // tries to get the technician from the database and logs whether the technician could be retrieved from the database in the console
    if (this.id) {
      this.technicianService.getTechnician(this.id)
        .subscribe(data => {
          this.technician = data;
        },
            error => {
          this.alertService.error('Technician could not be loaded.', false);
        });
    }
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the technician object to the database -- if the technician hasn't been created before, it saves as a new entry
    // if the technician has been created before, it updates the technician
    let response = !this.id ? this.technicianService.createTechnician(this.technician)
      : this.technicianService.updateTechnician(this.id, this.technician);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Technician saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The technician could not be saved.', false);
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
   * Resets the page back to the technicians list instead of the add technician page
   */
  gotoList() {
    this.router.navigate(['/technician/index']);
  }
}
