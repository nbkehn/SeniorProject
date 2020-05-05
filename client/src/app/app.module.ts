/**
 * Angular module dependencies
 *
 * @author Noah Trimble, Soumya Bagade, Will Duke
 */

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app.routing.module';
import { AppComponent } from './app.component';
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';

// import alert service and component
import { AlertComponent } from './alert/alert/alert.component';
import { AlertService } from './alert/alert.service';
import {NgxSmartModalModule} from "ngx-smart-modal";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
// import {ModifyAppointmentComponent} from "./appointment/modify-appointment/modify-appointment.component";
// import {AppointmentListComponent} from "./appointment/appointment-list/appointment-list.component";
import {ModifyCustomerComponent} from "./customer/modify-customer/modify-customer.component";
import {CustomerListComponent} from "./customer/customer-list/customer-list.component";
import {ModifyRsaComponent} from "./rsa/modify-rsa/modify-rsa.component";
import {RsaListComponent} from "./rsa/rsa-list/rsa-list.component";
import {ModifyUserComponent} from "./user/modify-user/modify-user.component";
import {UserListComponent} from "./user/user-list/user-list.component";
import {MustMatchDirective} from "./user/validator/must-match-directive";
import { ModifyFlooringComponent } from './flooring/modify-flooring/modify-flooring.component';
import { FlooringListComponent } from './flooring/flooring-list/flooring-list.component';
import {TemplateListComponent} from "./template/template-list/template-list.component";
import {ModifyTemplateComponent} from "./template/modify-template/modify-template.component";
import {ReminderListComponent} from "./reminder/reminder-list/reminder-list.component";
import {ModifyReminderComponent} from "./reminder/modify-reminder/modify-reminder.component";
import {CalendarComponent} from './calendar/calendar.component';
import { AddDialogComponent } from './add-dialog/add-dialog.component';
import { EditDialogComponent } from './edit-dialog/edit-dialog.component';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { MatMomentDateModule } from "@angular/material-moment-adapter";
import {ReminderErrorListComponent} from "./reminder-error/reminder-error-list/reminder-error-list.component";
//import { AppointmentCalendarComponent } from './appointment/appointment-calendar/appointment-calendar.component';
import {AssignmentCalendarComponent} from "./assignment/assignment-calendar/assignment-calendar.component";
import { MatOptionModule } from '@angular/material/core';
import {UploadSampleComponent} from './flooring/upload-sample/upload-sample.component';
import { OTWDialogComponent } from './otw-dialog/otw-dialog.component';
import {ViewDialogComponent} from './view-dialog/view-dialog.component';
import { CheckOutFormComponent } from './check-out/check-out-form/check-out-form.component';
import { CheckoutLandingComponent } from './checkout-landing/checkout-landing.component';
import { CheckOutComponent} from './check-out/check-out.component';
import { ZXingScannerComponent } from '@zxing/ngx-scanner';
import { CheckInComponent } from './check-in/check-in.component'
import {DragDropModule} from '@angular/cdk/drag-drop';
import { PrintAllQRComponent} from './print-all-qr/print-all-qr.component';
import { ViewInstallerDialogComponent } from './view-installer-dialog/view-installer-dialog.component';
import { GenerateReportComponent} from './generate-report/generate-report.component';


@NgModule({
  declarations: [
    AppComponent,
    ModifyTechnicianComponent,
    TechnicianListComponent,
    AlertComponent,
    // ModifyAppointmentComponent,
    // AppointmentListComponent,
    ModifyCustomerComponent,
    CustomerListComponent,
    ModifyRsaComponent,
    RsaListComponent,
    ModifyUserComponent,
    UserListComponent,
    MustMatchDirective,
    ModifyFlooringComponent,
    FlooringListComponent,
    TemplateListComponent,
    ModifyTemplateComponent,
    ReminderListComponent,
    ModifyReminderComponent,
    //AppointmentCalendarComponent,
    AssignmentCalendarComponent,
    CalendarComponent,
    AddDialogComponent,
    EditDialogComponent,
    DeleteDialogComponent,
    UploadSampleComponent,
    OTWDialogComponent,
    ReminderErrorListComponent,
    ViewDialogComponent,
    CheckOutFormComponent,
    CheckoutLandingComponent,
    CheckOutComponent,
    ZXingScannerComponent,
    ViewInstallerDialogComponent,
    CheckInComponent,
    PrintAllQRComponent,
    GenerateReportComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxSmartModalModule.forRoot(),
    NgbModule,
    MatDialogModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatMomentDateModule,
    DragDropModule,
  ],
  entryComponents: [
    AddDialogComponent,
    EditDialogComponent,
    DeleteDialogComponent,
    OTWDialogComponent,
    ViewDialogComponent,
    ViewInstallerDialogComponent,
  ],
  providers: [
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
