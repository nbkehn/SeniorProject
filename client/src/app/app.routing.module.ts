/**
 * Defines routing paths
 *
 * @author Noah Trimble, Soumya Bagade, Will Duke
 */
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';
// import {AppointmentListComponent} from "./appointment/appointment-list/appointment-list.component";
// import {ModifyAppointmentComponent} from "./appointment/modify-appointment/modify-appointment.component";
import {CustomerListComponent} from "./customer/customer-list/customer-list.component";
import {ModifyCustomerComponent} from "./customer/modify-customer/modify-customer.component";
import {RsaListComponent} from "./rsa/rsa-list/rsa-list.component";
import {ModifyRsaComponent} from "./rsa/modify-rsa/modify-rsa.component";
import {UserListComponent} from "./user/user-list/user-list.component";
import {ModifyUserComponent} from "./user/modify-user/modify-user.component";
import { ModifyFlooringComponent } from './flooring/modify-flooring/modify-flooring.component';
import { FlooringListComponent } from './flooring/flooring-list/flooring-list.component';
import {TemplateListComponent} from "./template/template-list/template-list.component";
import {ModifyTemplateComponent} from "./template/modify-template/modify-template.component";
import {ReminderListComponent} from "./reminder/reminder-list/reminder-list.component";
import {ModifyReminderComponent} from "./reminder/modify-reminder/modify-reminder.component";
import {ReminderErrorListComponent} from "./reminder-error/reminder-error-list/reminder-error-list.component";
import {AssignmentCalendarComponent} from "./assignment/assignment-calendar/assignment-calendar.component";
import { CalendarComponent } from './calendar/calendar.component';
import {UploadSampleComponent} from './flooring/upload-sample/upload-sample.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { CheckoutLandingComponent } from './checkout-landing/checkout-landing.component';
import { CheckOutFormComponent } from './check-out/check-out-form/check-out-form.component';
import { CheckInComponent } from './check-in/check-in.component';


const routes: Routes = [
  { path: '', redirectTo: 'appointment/index', pathMatch: 'full' },
  { path: 'technician/index', component: TechnicianListComponent },
  { path: 'technician/add', component: ModifyTechnicianComponent },
  { path: 'technician/edit/:id', component: ModifyTechnicianComponent },
  // { path: 'appointment/index', component:  AppointmentListComponent },
  // { path: 'appointment/add', component: ModifyAppointmentComponent },
  // { path: 'appointment/edit/:id', component: ModifyAppointmentComponent },
  { path: 'customer/index', component:  CustomerListComponent },
  { path: 'customer/add', component: ModifyCustomerComponent },
  { path: 'customer/edit/:id', component: ModifyCustomerComponent },
  { path: 'rsa/index', component:  RsaListComponent },
  { path: 'rsa/add', component: ModifyRsaComponent },
  { path: 'rsa/edit/:id', component: ModifyRsaComponent },
  { path: 'user/index', component:  UserListComponent },
  { path: 'user/add', component: ModifyUserComponent },
  { path: 'flooring/edit/:id', component: ModifyFlooringComponent },
  { path: 'flooring/index', component:  FlooringListComponent },
  { path: 'flooring/add', component: ModifyFlooringComponent },
  { path: 'user/edit/:id', component: ModifyUserComponent },
  { path: 'template/index', component: TemplateListComponent },
  { path: 'template/add', component: ModifyTemplateComponent },
  { path: 'template/edit/:id', component: ModifyTemplateComponent },
  { path: 'reminder/index', component: ReminderListComponent },
  { path: 'reminder/add', component: ModifyReminderComponent },
  { path: 'reminder/edit/:id', component: ModifyReminderComponent },
  { path: 'appointment/index', component: CalendarComponent },
  { path: 'reminderError/index', component: ReminderErrorListComponent },
  { path: 'appointment/calendar', component: CalendarComponent},
  { path: 'install/calendar', component: AssignmentCalendarComponent},
  { path: 'flooring/upload', component: UploadSampleComponent},
  { path: 'flooring/check-in', component: CheckInComponent},
  { path: 'flooring/check-out/create-customer', component: CheckOutFormComponent},
  { path: 'checkout-landing', component: CheckoutLandingComponent},
  { path: 'flooring/check-out/scan', component: CheckOutComponent},
  { path: 'install/calendar', component: AssignmentCalendarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
