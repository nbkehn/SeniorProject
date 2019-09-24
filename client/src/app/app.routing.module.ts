import { TechnicianDetailsComponent } from './technician-details/technician-details.component';
import { CreateTechnicianComponent } from './create-technician/create-technician.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TechnicianListComponent } from './technician-list/technician-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'technician', pathMatch: 'full' },
  { path: 'technicians', component: TechnicianListComponent },
  { path: 'add', component: CreateTechnicianComponent },
  { path: 'details/:id', component: TechnicianDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
