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

  /* Variable for the container of each dialog's form data */
  private formGroup: FormGroup

  /* Initializes form group so it can be accessed when the dialog opens */
  constructor(private builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.formGroup = this.builder.group({
      firstName: [this.firstName, [Validators.required]],
      lastName: [this.lastName, [Validators.required]],
    })
  }
  
  ngOnInit(): void {
  }

  getFirstName() {
    return this.firstName;
  }

  getLastName() {
    return this.lastName;
  }

  getFormGroup() {
    return this.formGroup;
  }

  /* Returns the form data when the dialog is closed */
  close() {
    this.dialogRef.close(this.formGroup.value);
  }

}
