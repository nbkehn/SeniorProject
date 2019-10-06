/**
 * Defines routing paths
 *
 * @author Noah Trimble
 */
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';
import {CustomerListComponent} from "./customer/customer-list/customer-list.component";
import {ModifyCustomerComponent} from "./customer/modify-customer/modify-customer.component";
import {RsaListComponent} from "./rsa/rsa-list/rsa-list.component";
import {ModifyRsaComponent} from "./rsa/modify-rsa/modify-rsa.component";

const routes: Routes = [
  { path: '', redirectTo: 'appointment/index', pathMatch: 'full' },
  { path: 'technician/index', component: TechnicianListComponent },
  { path: 'technician/add', component: ModifyTechnicianComponent },
  { path: 'technician/edit/:id', component: ModifyTechnicianComponent },
  { path: 'customer/index', component:  CustomerListComponent },
  { path: 'customer/add', component: ModifyCustomerComponent },
  { path: 'customer/edit/:id', component: ModifyCustomerComponent },
  { path: 'rsa/index', component:  RsaListComponent },
  { path: 'rsa/add', component: ModifyRsaComponent },
  { path: 'rsa/edit/:id', component: ModifyRsaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
