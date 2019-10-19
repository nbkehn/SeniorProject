/**
 * Angular component for creating and editing templates
 *
 * @package template
 * @author Noah Trimble
 * @author Soumya Bagade
 */
import { TemplateService } from '../template.service';
import { Template } from '../template';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';

@Component({
  selector: 'app-modify-template',
  templateUrl: './modify-template.component.html'
})
export class ModifyTemplateComponent implements OnInit {
  // the template's ID in the database
  id: number;
  // the template object to create and store data into
  template: Template = new Template();
  // the title for the page
  pageTitle: string;

  /**
   * Creates the instance of the component
   * @param route
   * @param templateService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private templateService: TemplateService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with template data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initializes a new template
    this.template = new Template();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the template has been stored in the database and is now being edited (Edit Template) or created as a new one (Create Template)
    this.pageTitle = this.id ? 'Edit Template' : 'Create Template';

    // if the id is not null, it means that the template has already been stored and is now being edited.
    // tries to get the template from the database and logs whether the template could be retrieved from the database in the console
    if (this.id) {
      this.templateService.getTemplate(this.id)
        .subscribe(data => {
          this.template = data;
        },
            error => {
          this.alertService.error('Template could not be loaded.', false);
        });
    }
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the template object to the database -- if the template hasn't been created before, it saves as a new entry
    // if the template has been created before, it updates the template
    let response = !this.id ? this.templateService.createTemplate(this.template)
      : this.templateService.updateTemplate(this.id, this.template);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Template saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The template could not be saved.', false);
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
   * Resets the page back to the templates list instead of the add template page
   */
  gotoList() {
    this.router.navigate(['/template/index']);
  }
}
