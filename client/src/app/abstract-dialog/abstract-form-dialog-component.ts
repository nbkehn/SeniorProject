import { Component, OnInit, Inject, Input } from '@angular/core';
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


/**
 * Abstract dialog class which defines the form fields and FormGroup
 * variable necessary to present a form in a modal (dialog) view.
 */
export abstract class AbstractFormDialogComponent implements OnInit {

  /* Variables for form data (to be shared with child classes) */
  private id: number;
  appointment: Appointment;

  @Input() private firstName: String = "";
  @Input() private lastName: String = "";
  @Input() private start: Date;
  @Input() private end: Date;
  customer: Customer;
  rsa: Rsa;
  technician: Technician;
  flooring: Flooring;

  public customerOptions: Customer[];
  // the list of technicians
  public technicianOptions: Technician[];
  // the list of rsas
  public rsaOptions: Rsa[];
  // this list of flooring options
  public flooringOptions: Flooring[];


  /* Variable for the container of each dialog's form data */
  private formGroup: FormGroup

  /* Initializes form group so it can be accessed when the dialog opens */
  constructor(private builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private appointmentService: AppointmentService,
    private customerService: CustomerService,
    private technicianService: TechnicianService,
    private rsaService: RsaService,
    private flooringService: FlooringService,
    private alertService: AlertService) {

    if (data) {
      this.id = data.id;
      const titleNamePortion: string = data.title;
      const fullName = titleNamePortion.split(":")[1].trim();
      const firstAndLastName = fullName.split(" ");
      this.setFirstName(firstAndLastName[0]);
      this.setLastName(firstAndLastName[1]);
      this.setStart(data.start || new Date());
      this.setEnd(data.end || this.getStart());
    }
    this.formGroup = this.builder.group({
      firstName: [this.firstName, [Validators.required]],
      lastName: [this.lastName, [Validators.required]],
      start: [this.start, [Validators.required]],
      end: [this.end, []],
      customer: [this.customer, []],
      technician: [this.technician, []],
      rsa: [this.rsa, []],
      flooring: [this.flooring, []],

    })
    console.log("Constructor");
  }

  ngOnInit() {


  }

  getFirstName() {
    return this.firstName;
  }

  setFirstName(name: string) {
    this.firstName = name;
  }

  getLastName() {
    return this.lastName;
  }

  setLastName(name: string) {
    this.lastName = name;
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

  getFormGroup() {
    return this.formGroup;
  }

  cancel() {
    this.dialogRef.close(false);
  }

  /**
  * populate technicians options
  */
  setTechnicians() {
    this.technicianService.getTechniciansList()
      .subscribe(
        data => {
          this.technicianOptions = data;
        },
        error => {
          this.alertService.error('Technicians could not be loaded.', false);
        });
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
    try {
      if (returnObject.end < returnObject.start) {
        throw new Error("Error: end date cannot be earlier than start date");
      }
      if (!returnObject.start) {
        throw new Error("Error: start date is required");
      }
      if (!returnObject.firstName || returnObject.firstName == "") {
        throw new Error("Error: first name is required");
      }
      if (!returnObject.lastName || returnObject.lastName == "") {
        throw new Error("Error: last name is required");
      }
      this.dialogRef.close(returnObject);
    } catch (e) {
      alert(e.message);
    }
  }

}
