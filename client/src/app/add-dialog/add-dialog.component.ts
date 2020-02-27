import { Component, OnInit, Inject, Input, NgModule } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AbstractFormDialogComponent } from '../abstract-dialog/abstract-form-dialog-component';

import { MatFormFieldModule } from '@angular/material/form-field';

import { Customer } from '../customer/customer';
import { Technician } from '../technician/technician';
import { MatOption } from '@angular/material/core';
import { Rsa } from '../rsa/rsa';
import { Flooring } from '../flooring/flooring';
import { Appointment } from '../appointment/appointment';

import { AlertService } from '../alert/alert.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { TechnicianService } from 'src/app/technician/technician.service';
import { RsaService } from 'src/app/rsa/rsa.service';
import { FlooringService } from 'src/app/flooring/flooring.service';
import { AppointmentService } from '../appointment/appointment.service';


import { MatSelectModule } from '@angular/material/select';


@Component({
  selector: 'app-add-dialog',
  templateUrl: './add-dialog.component.html',
  styleUrls: ['./add-dialog.component.less']
})
export class AddDialogComponent extends AbstractFormDialogComponent implements OnInit {

  private minDate: Date;

  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
   appointmentService: AppointmentService,
   customerService: CustomerService,
   technicianService: TechnicianService,
   rsaService: RsaService,
   flooringService: FlooringService,
   alertService: AlertService,) {

    super(builder, dialogRef, data, appointmentService, customerService, technicianService, rsaService,
      flooringService, alertService);
    this.minDate = new Date();

  }

  ngOnInit() {

    // initialize mapped options
    this.customerOptions = [];
    this.technicianOptions = [];
    this.rsaOptions = [];
    this.flooringOptions = [];

    // populate option data
    this.setCustomers();
    this.setTechnicians();
    this.setRSAs();
    this.setFloorings();

    // initialize a new appointment and aggregates
    this.appointment = new Appointment();
    this.appointment.customer = new Customer();
    this.appointment.technicians = [];
    this.appointment.rsa = new Rsa();
    this.appointment.flooring = new Flooring();

    console.log(this.customerOptions.toString);

  }



  /**
   * Creates a new appointment based on the form fields entered by the user
   */
  add() {
    this.close();
  }

}
