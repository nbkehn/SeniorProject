import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder } from '@angular/forms';
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
 * choice to send an "on the way" message for that appointment.
 * 
 * @author Renee Segda
 */
@Component({
  selector: 'app-otw-dialog',
  templateUrl: './otw-dialog.component.html',
  styleUrls: ['./otw-dialog.component.less']
})
export class OTWDialogComponent extends AbstractFormDialogComponent implements OnInit {

    /**
   * Constructor which calls the super constructor from the AbstractFormDialogComponent
   * class.
   * @param builder A FormBuilder used to connect fields corresponding to appointment data to their corresponding form fields.
   * @param dialogRef A reference to the dialog box which appears in the user interface
   * @param data  Data which has been passed to this component from the main CalendarComponent.
   * @param appointmentService A service class which performs the HTTP request to send the message for the appointment.
   * @param customerService A service class which retrieves a list of all customers in the system. It is necessary for the super
   *        constructor but is not used here as the user's only choice is whether to send the message or not.
   * @param technicianService A service class which retrieves a list of all installation technicians in the system. It is necessary
   *        for the super constructor but is not used here.
   * @param rsaService A service class which retrieves a list of all regional sales associates in the system. It is necessary
   *        for the super constructor but is not used here.
   * @param flooringService A service class which retrieves a list of all flooring samples in the system. It is necessary
   *        for the super constructor but is not used here.
   * @param alertService A service used to make various types of alerts appear in the user interface (i.e. success, error, etc.)
   */
  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
   appointmentService: AppointmentService, customerService: CustomerService, technicianService: TechnicianService, rsaService: RsaService,
   flooringService: FlooringService, alertService: AlertService) {
     super(builder, dialogRef, data, appointmentService, customerService, technicianService, rsaService, flooringService, alertService)
  }

  /**
   * Closes the dialog box and returns the boolean value "true"
   * to the CalendarComponent indicating the user wishes for the
   * message to be sent.
   */
  send() {
    this.dialogRef.close(true);
  }

    /**
   * Closes the dialog box and returns the boolean value "false"
   * to the CalendarComponent indicating the user doesn't wish
   * for the message to be sent.
   */
  cancel() {
    this.dialogRef.close(false);
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
