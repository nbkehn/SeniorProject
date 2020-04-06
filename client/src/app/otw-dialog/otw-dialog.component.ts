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

@Component({
  selector: 'app-otw-dialog',
  templateUrl: './otw-dialog.component.html',
  styleUrls: ['./otw-dialog.component.less']
})
export class OTWDialogComponent extends AbstractFormDialogComponent implements OnInit {

  formGroup: FormGroup

  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
   appointmentService: AppointmentService, customerService: CustomerService, technicianService: TechnicianService, rsaService: RsaService,
   flooringService: FlooringService, alertService: AlertService) {
     super(builder, dialogRef, data, appointmentService, customerService, technicianService, rsaService, flooringService, alertService)
  }

  ngOnInit(): void {
  }

  send() {
    this.dialogRef.close(true);
  }

  cancel() {
    this.dialogRef.close(false);
  }

  formatPhoneString(phoneNumber: string) {
    if (!phoneNumber || phoneNumber.length != 10) {
      return "";
    } else {
      const areaCode = phoneNumber.substring(0, 3);
      const first3 = phoneNumber.substring(3, 6);
      const last4 = phoneNumber.substring(6, 10);
      return `(${areaCode}) ${first3}-${last4}`;
    }
  }

}
