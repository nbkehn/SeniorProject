/**
 * Angular module dependencies
 *
 * @author Noah Trimble
 * @author Soumya Bagade
 */

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app.routing.module';
import { AppComponent } from './app.component';
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

// import alert service and component
import { AlertComponent } from './alert/alert/alert.component';
import { AlertService } from './alert/alert.service';
import {NgxSmartModalModule} from "ngx-smart-modal";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ModifyCustomerComponent} from "./customer/modify-customer/modify-customer.component";
import {CustomerListComponent} from "./customer/customer-list/customer-list.component";
import {ModifyRsaComponent} from "./rsa/modify-rsa/modify-rsa.component";
import {RsaListComponent} from "./rsa/rsa-list/rsa-list.component";
import {ModifyUserComponent} from "./user/modify-user/modify-user.component";
import {UserListComponent} from "./user/user-list/user-list.component";
import {MustMatchDirective} from "./user/validator/must-match-directive";
import {TemplateListComponent} from "./template/template-list/template-list.component";
import {ModifyTemplateComponent} from "./template/modify-template/modify-template.component";
import {ReminderListComponent} from "./reminder/reminder-list/reminder-list.component";
import {ModifyReminderComponent} from "./reminder/modify-reminder/modify-reminder.component";
import {CalendarComponent} from './calendar/calendar.component';
import { AddDialogComponent } from './add-dialog/add-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    ModifyTechnicianComponent,
    TechnicianListComponent,
    AlertComponent,
    ModifyCustomerComponent,
    CustomerListComponent,
    ModifyRsaComponent,
    RsaListComponent,
    ModifyUserComponent,
    UserListComponent,
    MustMatchDirective,
    TemplateListComponent,
    ModifyTemplateComponent,
    ReminderListComponent,
    ModifyReminderComponent,
    CalendarComponent,
    AddDialogComponent,
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
  ],
  entryComponents: [
    AddDialogComponent
  ],
  providers: [
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
