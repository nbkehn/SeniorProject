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
 * Component which stores the data regarding one of the appointments
 * to which teams can be assigned.
 * 
 * @author Renee Segda
 */
@Component({
  selector: 'app-view-installer-dialog',
  templateUrl: './view-installer-dialog.component.html',
  styleUrls: ['./view-installer-dialog.component.less']
})
export class ViewInstallerDialogComponent extends AbstractFormDialogComponent implements OnInit {

  private numOfDays: number;
  private dayNumber: number;

    /**
   * Constructor which calls the super constructor from the AbstractFormDialogComponent
   * class.
   * @param builder A FormBuilder used to connect fields corresponding to appointment data to their corresponding form fields.
   * @param dialogRef A reference to the dialog box which appears in the user interface
   * @param data  Data which has been passed to this component from the main AssignmentCalendarcomponent.
   * @param appointmentService A service class which performs HTTP requests for appointments. It is necessary for the super
   *        constructor but is not used here because the only purpose of this dialog is to view the appointment's details.
   * @param customerService A service class which retrieves a list of all customers in the system. It is necessary for the super
   *        constructor but is not used here.
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

      this.dayNumber = data.dayNumber;
      this.numOfDays = data.numOfDays;
  }

    /**
   * Checks if the given phone number is in the (###) ###-####
   * format. If it is, it returns the given string. If not, it
   * formats the string (assumed to be in ########## format) into
   * the first format and returns that formatted string.
   * @param phoneNumber The phone number string to be formatted.
   */
  formatPhoneString(phoneNumber: string) {
    if (!phoneNumber || phoneNumber.length != 10) {
      return phoneNumber;
    } else {
      const areaCode = phoneNumber.substring(0, 3);
      const first3 = phoneNumber.substring(3, 6);
      const last4 = phoneNumber.substring(6, 10);
      return `(${areaCode}) ${first3}-${last4}`;
    }
  }

}
