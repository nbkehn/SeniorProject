import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {AbstractFormDialogComponent} from '../abstract-dialog/abstract-form-dialog-component'

@Component({
  selector: 'app-add-dialog',
  templateUrl: './add-dialog.component.html',
  styleUrls: ['./add-dialog.component.less']
})
export class AddDialogComponent extends AbstractFormDialogComponent implements OnInit {
  
  private minDate: Date;

  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    super(builder, dialogRef, data);
    this.minDate = new Date();
  }

  ngOnInit(): void {
  }

  /**
   * Creates a new appointment based on the form fields entered by the user
   */
  add() {
    this.close();
  }

}
