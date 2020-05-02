import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder } from '@angular/forms';
import {AbstractFormDialogComponent} from '../abstract-dialog/abstract-form-dialog-component';

import { AlertService } from '../alert/alert.service';
import { CustomerService } from '../customer/customer.service';
import { TechnicianService } from '../technician/technician.service';
import { RsaService } from '../rsa/rsa.service';
import { FlooringService } from '../flooring/flooring.service';
import { AppointmentService } from '../appointment/appointment.service';
import { Technician } from '../technician/technician';
import { Customer } from '../customer/customer';
import { Rsa } from '../rsa/rsa';
import { Flooring } from '../flooring/flooring';

/**
 * Component which stores the data provided by the user through the dialog
 * box regarding the update of an appointment in the calendar.
 * 
 * @author Renee Segda
 */
@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-dialog.component.html',
  styleUrls: ['./edit-dialog.component.less']
})
export class EditDialogComponent extends AbstractFormDialogComponent implements OnInit {

  /**
   * The customer originally associated with the currently selected appointment.
   */
  oldCustomer : Customer;

  /**
   * The RSA originally associated with the currently selected appointment.
   */
  oldRsa : Rsa;

  /**
   * The flooring sample originally associated with the currently selected appointment.
   */
  oldFlooring : Flooring;

  /**
   * The list of installation technicians originally associated with the currently
   * selected appointment.
   */
  oldTechnicians : Technician[];


  /**
   * Constructor which calls the super constructor from the AbstractFormDialogComponent
   * class.
   * @param builder A FormBuilder used to connect fields corresponding to appointment data to their corresponding form fields
   * @param dialogRef A reference to the dialog box which appears in the user interface
   * @param data  Data which has been passed to this component from the main CalendarComponent.
   * @param appointmentService A service class which performs the HTTP request to update the appointment in the database.
   * @param customerService A service class which retrieves a list of all customers in the system. The user can choose a
   *        customer from this list to associate with the appointment.
   * @param technicianService A service class which retrieves a list of all installation technicians in the system. The user
   *        can choose one or more technicians from this list to associate with the appointment.
   * @param rsaService A service class which retrieves a list of all regional sales associates in the system. The user can
   *        choose an RSA from this list to associate with the appointment.
   * @param flooringService A service class which retrieves a list of all flooring samples in the system. The user can choose a
   *        flooring sample from this list to associate with the appointment.
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
   * Initializes the list of customers, installation technicians, RSAs, and flooring sample options
   * from which the user can choose. This method runs before the dialog box opens in the user interface.
   */
  ngOnInit() {
      this.selectedAppointment.subscribe(data => {
        this.oldCustomer = data.customer;
        this.oldRsa = data.rsa;
        this.oldFlooring = data.flooring;
      })
     // initialize mapped options
     this.customerOptions = [];
     this.rsaOptions = [];
     this.flooringOptions = [];
 
     // populate option data
     this.setCustomers();
     this.setRSAs();
     this.setFloorings();
  }

  /**
   * Calls the superclass's close() method to return the current state of the
   * appointment to the CalendarComponent class to be updated.
   */
  edit() {
    this.close();
  }



}
