/**
 * Angular component for creating and editing flooring
 *
 * @package flooring
 * @author Noah Trimble, Will Duke
 */
import { FlooringService } from '../flooring.service';
import { Flooring } from '../flooring';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-flooring',
  templateUrl: './modify-flooring.component.html'
})
export class ModifyFlooringComponent implements OnInit {
  // the flooring's ID in the database
  id: number;
  // the flooring object to create and store data into
  flooring: Flooring = new Flooring();
  // the title for the page
  title: string;

  checkedOption: string;

  //holds flooring type for required fields
  types = [
    { value: 'carpet', viewValue: 'Carpet' },
    { value: 'hardwood', viewValue: 'Hardwood' },
  ];

  selectedType: string;

  /**
   * Creates the instance of the component
   * @param route
   * @param flooringService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
    private flooringService: FlooringService,
    private router: Router,
    private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with flooring data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initializes a new flooring
    this.flooring = new Flooring();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the flooring has been stored in the database and is now being edited (Edit Flooring) or created as a new one (Create Flooring)
    this.title = this.id ? 'Edit Flooring' : 'Create Flooring';

    // if the id is not null, it means that the flooring has already been stored and is now being edited.
    // tries to get the flooring from the database and logs whether the flooring could be retrieved from the database in the console
    if (this.id) {
      this.flooringService.getFlooring(this.id)
        .subscribe(data => {
          this.flooring = data;
        },
          error => {
            this.alertService.error('Flooring could not be loaded.', false);
          });
    }
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the flooring object to the database -- if the flooring hasn't been created before, it saves as a new entry
    // if the flooring has been created before, it updates the flooring
    let response = !this.id ? this.flooringService.createFlooring(this.flooring)
      : this.flooringService.updateFlooring(this.id, this.flooring);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Flooring saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The flooring could not be saved.', false);
      });
  }

  /**
   * This method is called on submit.
   * Calls on the save method to save the entry to the database.
   */
  onSubmit() {
    console.log(this.checkedOption);
    if (this.checkedOption == "no")
      this.flooring.checkedOut = false;
    else
      this.flooring.checkedOut = true;

    console.log(this.flooring);
    this.save();
  }

  /**
   * Resets the page back to the floorings list instead of the add flooring page
   */
  gotoList() {
    this.router.navigate(['/flooring/index']);
  }

  changeType() {
    console.log(this.flooring);

  }
}
