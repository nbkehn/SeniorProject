import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {AbstractFormDialogComponent} from '../abstract-dialog/abstract-form-dialog-component';

import { AlertService } from '../alert/alert.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { TechnicianService } from 'src/app/technician/technician.service';
import { RsaService } from 'src/app/rsa/rsa.service';
import { FlooringService } from 'src/app/flooring/flooring.service';
import { AppointmentService } from '../appointment/appointment.service';
import { Appointment } from '../appointment/appointment';
import { Data } from '@angular/router';
import { Technician } from '../technician/technician';
import { Customer } from '../customer/customer';
import { Rsa } from '../rsa/rsa';
import { Flooring } from '../flooring/flooring';

@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-dialog.component.html',
  styleUrls: ['./edit-dialog.component.less']
})
export class EditDialogComponent extends AbstractFormDialogComponent implements OnInit {

  oldCustomer : Customer;
  oldRsa : Rsa;
  oldFlooring : Flooring;
  oldTechnicians : Technician[];
  private minDate: Date;


  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
   appointmentService: AppointmentService,
   customerService: CustomerService,
   technicianService: TechnicianService,
   rsaService: RsaService,
   flooringService: FlooringService,
   alertService: AlertService) {

    super(builder, dialogRef, data, appointmentService, customerService, technicianService, rsaService,
      flooringService, alertService);

      this.minDate = new Date();

    }

  ngOnInit() {
      this.selectedAppointment.subscribe(data => {
        this.oldCustomer = data.customer;
        this.oldRsa = data.rsa;
        this.oldFlooring = data.flooring;
        this.technicians = data.technicians;
      })
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
    
 
  }

  edit() {
    this.close();
    // this.dialogRef.close(this.formGroup.value);
  }



}
