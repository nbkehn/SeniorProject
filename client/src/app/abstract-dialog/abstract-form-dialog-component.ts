import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

/**
 * Abstract dialog class which defines the form fields and FormGroup
 * variable necessary to present a form in a modal (dialog) view.
 */
export abstract class AbstractFormDialogComponent implements OnInit {

  /* Variables for form data (to be shared with child classes) */
  @Input() private firstName: String = "";
  @Input() private lastName: String = "";
  @Input() private start: Date;
  @Input() private end: Date;

  /* Variable for the container of each dialog's form data */
  private formGroup: FormGroup

  /* Initializes form group so it can be accessed when the dialog opens */
  constructor(private builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    if (data) {
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
      end: [this.end, [Validators.required]],
    })
  }
  
  ngOnInit(): void {
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

  /* Returns the form data when the dialog is closed */
  close(eventDeleted: boolean = false) {
    this.dialogRef.close({
      ...this.formGroup.value,
      deleted: eventDeleted
    });
  }

}
