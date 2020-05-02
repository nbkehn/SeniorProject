import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {AbstractFormDialogComponent} from '../abstract-dialog/abstract-form-dialog-component';

import { AlertService } from '../alert/alert.service';
import { CustomerService } from '../customer/customer.service';
import { TechnicianService } from '../technician/technician.service';
import { RsaService } from '../rsa/rsa.service';
import { FlooringService } from '../flooring/flooring.service';
import { AppointmentService } from '../appointment/appointment.service';

/**
 * Component which stores the data regarding the currently selected
 * appointment, displays it in a dialog box, and gives the user the
 * choice to delete that appointment.
 * 
 * @author Renee Segda
 */
@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.less']
})
export class DeleteDialogComponent extends AbstractFormDialogComponent implements OnInit {

  /**
   * Constructor which calls the super constructor from the AbstractFormDialogComponent
   * class.
   * @param builder A FormBuilder used to connect fields corresponding to appointment data to their corresponding form fields.
   * @param dialogRef A reference to the dialog box which appears in the user interface
   * @param data  Data which has been passed to this component from the main CalendarComponent.
   * @param appointmentService A service class which performs the HTTP request to delete the appointment from the database.
   * @param customerService A service class which retrieves a list of all customers in the system. It is necessary for the super
   *        constructor but is not used here as the user's only choice is whether to delete the appointment or not.
   * @param technicianService A service class which retrieves a list of all installation technicians in the system. It is necessary
   *        for the super constructor but is not used here.
   * @param rsaService A service class which retrieves a list of all regional sales associates in the system. It is necessary
   *        for the super constructor but is not used here.
   * @param flooringService A service class which retrieves a list of all flooring samples in the system. It is necessary
   *        for the super constructor but is not used here.
   * @param alertService A service used to make various types of alerts appear in the user interface (i.e. success, error, etc.)
   */
  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
   appointmentService: AppointmentService,
   customerService: CustomerService,
   technicianService: TechnicianService,
   rsaService: RsaService,
   flooringService: FlooringService,
   alertService: AlertService) {

    super(builder, dialogRef, data, appointmentService, customerService, technicianService, rsaService,
      flooringService, alertService);
  }

  /**
   * Calls the superclass's close() method with the deleted parameter being true
   * (since the user chose to delete the appointment).
   */
  delete() {
    this.close(true);
  }

}
