import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {AbstractFormDialogComponent} from '../abstract-dialog/abstract-form-dialog-component'

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.less']
})
export class DeleteDialogComponent extends AbstractFormDialogComponent implements OnInit {

  constructor(builder: FormBuilder, public dialogRef: MatDialogRef<AbstractFormDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    super(builder, dialogRef, data);
    debugger;
  }

  ngOnInit(): void {
  }

  delete() {
    this.close(true);
  }

}
