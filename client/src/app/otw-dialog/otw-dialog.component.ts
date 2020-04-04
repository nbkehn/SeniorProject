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
export class OTWDialogComponent implements OnInit {

  formGroup: FormGroup

  /**
   * A list of dates between the start
   * and end date of the appointment. The
   * user shall be able to choose a day of
   * the appointment for which to send the
   * OTW message.
   */
  private availableDates: Date []

  /**
   * The appointment date chosen by the user
   */
  @Input() dateForOTW: Date;

  private currentAppointment: Appointment

  /**
   * Placeholder text for the dropdown box
   * from which the user will select an appointment
   * date for which to send a message.
  */
  private placeholderString = "Select a date";

  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
   appointmentService: AppointmentService,) {

    this.availableDates = [];
    var iterDate: Date = data.start;
    const dayLength = Date.parse("01/02/2000") - Date.parse("01/01/2000");
    while (iterDate <= data.end) {
      this.availableDates.push(iterDate);
      iterDate = new Date(iterDate.valueOf() + dayLength);
    }
      const id = data.id;
      //retrieve appointment from database based on event id
      appointmentService.getAppointment(id).subscribe(
        response => {
          this.currentAppointment = response;
        });
    this.formGroup = builder.group({
      dateForOTW: [this.dateForOTW, Validators.required]
    })
  }

  ngOnInit(): void {
  }

  send() {
    this.dialogRef.close(this.formGroup.value);
  }

  /**
   * Parses a Date object and returns a string with the format MM/DD/YYYY
   * @param date The date to be processed
   * @return A formatted string
   */
  formatDateString(date: Date) {
    const dayVal = date.getDate();
    const monthVal = date.getMonth() + 1; // Month values go from 0-11
    const yearVal = date.getFullYear();
    const dayString = dayVal < 10 ? `0${dayVal}` : dayVal.toString();
    const monthString = monthVal < 10 ? `0${monthVal}` : monthVal.toString();
    return `${monthString}/${dayString}/${yearVal}`
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
