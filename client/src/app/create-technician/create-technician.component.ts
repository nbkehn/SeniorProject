import { TechnicianService } from '../technician.service';
import { Technician } from '../technician';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-technician',
  templateUrl: './create-technician.component.html',
  styleUrls: ['./create-technician.component.less']
})
export class CreateTechnicianComponent implements OnInit {
  // the technician's ID in the database
  id: number;
  // the technician object to create and store data into
  technician: Technician = new Technician();
  // whether the technician has been added to/updated in the database
  submitted = false;
  // the title for the page
  title: string;

  /**
   * Creates the instance of the component
   * @param route 
   * @param technicianService 
   * @param router 
   */
  constructor(private route: ActivatedRoute,
              private technicianService: TechnicianService,
              private router: Router) { }

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
          console.log(data)
          this.technician = data;
        }, error => console.log(error));
    }
  }

  /**
   * This initializes the technician object and sets the submitted flag to false (because it's a new entry)
   */
  newTechnician(): void {
    this.submitted = false;
    this.technician = new Technician();
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
    response.subscribe(data => console.log(data), error => console.log(error));
    // resets the page back to the technicians list
    this.gotoList();
  }

  /**
   * This method is called on submit. 
   * It changes the submitted boolean to true (which shows the success message and hides the form).
   * It then calls on the save method to save the entry to the database.
   */
  onSubmit() {
    this.submitted = true;
    this.save();
  }

  /**
   * Resets the page back to the technicians list instead of the add technician page
   */
  gotoList() {
    this.router.navigate(['/technicians']);
  }
}
