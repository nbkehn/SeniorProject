import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder } from '@angular/forms';
import { AbstractFormDialogComponent } from '../abstract-dialog/abstract-form-dialog-component';


import { Customer } from '../customer/customer';
import { Technician } from '../technician/technician';
import { Rsa } from '../rsa/rsa';
import { Flooring } from '../flooring/flooring';
import { Appointment } from '../appointment/appointment';

import { AlertService } from '../alert/alert.service';
import { CustomerService } from '../customer/customer.service';
import { TechnicianService } from '../technician/technician.service';
import { RsaService } from '../rsa/rsa.service';
import { FlooringService } from '../flooring/flooring.service';
import { AppointmentService } from '../appointment/appointment.service';


/**
 * Component which stores the data provided by the user through the dialog
 * box regarding the addition of a new appointment to the calendar.
 * 
 * @author Renee Segda
 */
@Component({
  selector: 'app-add-dialog',
  templateUrl: './add-dialog.component.html',
  styleUrls: ['./add-dialog.component.less']
})
export class AddDialogComponent extends AbstractFormDialogComponent implements OnInit {


  /**
   * Constructor which calls the super constructor from the AbstractFormDialogComponent
   * class and initializes the minDate field to today's date.
   * @param builder A FormBuilder used to connect fields corresponding to appointment data to their corresponding form fields
   * @param dialogRef A reference to the dialog box which appears in the user interface
   * @param data  Data which has been passed to this component from the main CalendarComponent. However, no appointment info
   *        need be passed to this component as its purpose is to add a new appointment.
   * @param appointmentService A service class which performs the HTTP request to add the new appointment to the database.
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
   alertService: AlertService,) {

    super(builder, dialogRef, data, appointmentService, customerService, technicianService, rsaService,
      flooringService, alertService);

  }

  /**
   * Initializes the list of customers, installation technicians, RSAs, and flooring sample options
   * from which the user can choose. Also initializes an empty appointment object to be updated based
   * on input from the user. This method runs before the dialog box opens in the user interface.
   */
  ngOnInit() {

    // initialize mapped options
    this.customerOptions = [];
    this.rsaOptions = [];
    this.flooringOptions = [];

    // populate option data
    this.setCustomers();
    this.setRSAs();
    this.setFloorings();

    // initialize a new appointment and aggregates
    this.appointment = new Appointment();
    this.appointment.customer = new Customer();
    this.appointment.rsa = new Rsa();
    this.appointment.flooring = new Flooring();

  }



    /**
   * Calls the superclass's close() method to return the current state of the
   * appointment to the CalendarComponent class to be added to the database.
   */
  add() {
    console.log(this.formGroup.value);
    this.close();
    
    }

}
