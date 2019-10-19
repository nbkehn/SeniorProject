/**
 * Angular component for viewing templates
 *
 * @package template
 * @author Noah Trimble
 * @author Soumya Bagade
 */
import { Observable } from "rxjs";
import { TemplateService } from "../template.service";
import { Template } from "../template";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-template-list",
  templateUrl: "./template-list.component.html"
})
export class TemplateListComponent implements OnInit {
  // the list of templates
  templates: Observable<Template[]>;

  /**
   * Constructor for the TemplateListComponent, doesn't really do anything right now
   * @param templateService the service for the template component
   * @param router the router to route the templates to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private templateService: TemplateService,
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
   * reloads the data with the most updated list of templates from the database
   * calls from the template service so that it makes the DB call
   */
  reloadData() {
    this.templates = this.templateService.getTemplatesList();
  }

  /**
   * deletes the template from the database and prints the response to the console
   * @param id the id of the template to delete
   */
  deleteTemplate(id: number) {
    this.templateService.deleteTemplate(id)
      .subscribe(
        data => {
          this.alertService.success('Template was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('Template could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the template to edit
   */
  editTemplate(id: number) {
    this.router.navigate(['/template/edit', id]);
  }
}
