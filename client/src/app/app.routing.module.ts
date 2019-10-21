/**
 * Defines routing paths
 *
 * @author Noah Trimble
 * @modifiedBy Soumya Bagade
 */
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';
import {AppointmentListComponent} from "./appointment/appointment-list/appointment-list.component";
import {ModifyAppointmentComponent} from "./appointment/modify-appointment/modify-appointment.component";
import {CustomerListComponent} from "./customer/customer-list/customer-list.component";
import {ModifyCustomerComponent} from "./customer/modify-customer/modify-customer.component";
import {RsaListComponent} from "./rsa/rsa-list/rsa-list.component";
import {ModifyRsaComponent} from "./rsa/modify-rsa/modify-rsa.component";
import {UserListComponent} from "./user/user-list/user-list.component";
import {ModifyUserComponent} from "./user/modify-user/modify-user.component";
import {TemplateListComponent} from "./template/template-list/template-list.component";
import {ModifyTemplateComponent} from "./template/modify-template/modify-template.component";

const routes: Routes = [
  { path: '', redirectTo: 'appointment/index', pathMatch: 'full' },
  { path: 'technician/index', component: TechnicianListComponent },
  { path: 'technician/add', component: ModifyTechnicianComponent },
  { path: 'technician/edit/:id', component: ModifyTechnicianComponent },
  { path: 'appointment/index', component:  AppointmentListComponent },
  { path: 'appointment/add', component: ModifyAppointmentComponent },
  { path: 'appointment/edit/:id', component: ModifyAppointmentComponent },
  { path: 'customer/index', component:  CustomerListComponent },
  { path: 'customer/add', component: ModifyCustomerComponent },
  { path: 'customer/edit/:id', component: ModifyCustomerComponent },
  { path: 'rsa/index', component:  RsaListComponent },
  { path: 'rsa/add', component: ModifyRsaComponent },
  { path: 'rsa/edit/:id', component: ModifyRsaComponent },
  { path: 'user/index', component:  UserListComponent },
  { path: 'user/add', component: ModifyUserComponent },
  { path: 'user/edit/:id', component: ModifyUserComponent },
  { path: 'template/index', component: TemplateListComponent },
  { path: 'template/add', component: ModifyTemplateComponent },
  { path: 'template/edit/:id', component: ModifyTemplateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
