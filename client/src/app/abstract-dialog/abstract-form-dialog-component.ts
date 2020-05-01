import { Component, OnInit, Inject, Input } from '@angular/core';
import { filter, map } from 'rxjs/operators';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
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
import { Observable, BehaviorSubject } from 'rxjs';
import * as moment from 'moment';


/**
 * Abstract dialog class which defines the form fields and FormGroup
 * variable necessary to present a form in a modal (dialog) view.
 */
export abstract class AbstractFormDialogComponent implements OnInit {

  /* Variables for form data (to be shared with child classes) */

  selectedAppointment = new BehaviorSubject<Appointment>(new Appointment());
  appStore: { app: Appointment } = { app: new Appointment() };
  readonly app = this.selectedAppointment.asObservable();

  private id: number;
  appointment: Appointment;
  retrievedApp = new Appointment();
  editApp: Appointment;
  editing: boolean = false;


  @Input() start: Date;
  @Input() end: Date;
  customer: Customer;
  rsa: Rsa;
  flooring: Flooring;

  public customerOptions: Customer[];
  // the list of rsas
  public rsaOptions: Rsa[];
  // this list of flooring options
  public flooringOptions: Flooring[];


  /* Variable for the container of each dialog's form data */
  formGroup: FormGroup


  /* Initializes form group so it can be accessed when the dialog opens */
  constructor(public builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private appointmentService: AppointmentService,
    private customerService: CustomerService,
    private technicianService: TechnicianService,
    private rsaService: RsaService,
    private flooringService: FlooringService,
    private alertService: AlertService) {
    if (data) {
      this.id = data.id;
      //retrieve appointment from database based on event id
      this.appointmentService.getAppointment(this.id).subscribe(
        data => {
          this.editApp = data;
          this.update();
        });

    }
    this.formGroup = this.builder.group({
      start: [this.start, [Validators.required]],
      end: [this.end, []],
      customer: [this.customer, []],
      rsa: [this.rsa, []],
      flooring: [this.flooring, []],

    });

  }

  ngOnInit() {

  }

  getStart() {
    return this.start;
  }

  setStart(start: Date) {
    this.start = start;
  }

  getEnd() {
    return this.end;
  }

  setEnd(end: Date) {
    this.end = end;
  }

  setCustomer(customer: Customer) {
    this.customer = customer;
  }

  setRsa(rsa: Rsa) {
    this.rsa = rsa;
  }

  setFlooring(flooring: Flooring) {
    this.flooring = flooring;
  }

  getFlooringString(flooring: Flooring) {
    if (!flooring) {
      return "";
    }
    var string = flooring.name;
    if (flooring.style) {
      string = `${string} ${flooring.style}`;
    }
    if (flooring.color) {
      string = `${string} ${flooring.color}`;
    }
    return string;
  }

  getFormGroup() {
    return this.formGroup;
  }

  cancel() {
    this.dialogRef.close(false);
  }

  /**
   * populate customers options
   */
  setCustomers() {
    this.customerService.getCustomersList()
      .subscribe(
        data => {
          this.customerOptions = data;
        },
        error => {
          this.alertService.error('Customers could not be loaded.', false);
        });
  }

  /**
   * populate RSA options
   */
  setRSAs() {
    this.rsaService.getRSAsList()
      .subscribe(
        data => {
          this.rsaOptions = data;
        },
        error => {
          this.alertService.error('RSAs could not be loaded.', false);
        });
  }

  /**
  * populate Flooring options
  */
  setFloorings() {
    this.flooringService.getFlooringsList()
      .subscribe(
        data => {
          this.flooringOptions = data;
        },
        error => {
          this.alertService.error('Floorings could not be loaded.', false);
        });
  }

  getCustomer() {
    return this.customer;
  }

  update() {
    console.log(this.editApp);
    this.setStart(moment(this.editApp.startDate).toDate());
    this.setEnd(moment(this.editApp.endDate).toDate());
    this.setCustomer(this.editApp.customer);
    this.setRsa(this.editApp.rsa);
    this.setFlooring(this.editApp.flooring);
    this.formGroup = this.builder.group({
      start: [this.start, [Validators.required]],
      end: [this.end, []],
      customer: [this.customer, []],
      rsa: [this.rsa, []],
      flooring: [this.flooring, []],

    })
  }

  /* Returns the form data when the dialog is closed */
  close(eventDeleted: boolean = false) {
    const returnObject = {
      id: this.id,
      ...this.formGroup.value,
      deleted: eventDeleted
    };
    if (!returnObject.end) {
      returnObject.end = returnObject.start;
    }
    var invalidResult = false;
    if (!eventDeleted) {
      try {
        if (returnObject.end < returnObject.start) {
          throw new Error("Error: end date cannot be earlier than start date");
        }
        if (!returnObject.start) {
          throw new Error("Error: start date is required");
        }
        if (!returnObject.customer) {
          throw new Error("Error: customer is required");
        }
        if (!returnObject.rsa) {
          throw new Error("Error: RSA is required");
        }
        if (!returnObject.flooring) {
          throw new Error("Error: flooring is required");
        }
      } catch (e) {
        alert(e.message);
        invalidResult = true;
      }
    }
    if (!invalidResult) {
      this.dialogRef.close(returnObject);
    }
  }

}
