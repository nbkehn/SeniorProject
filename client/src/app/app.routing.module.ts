/**
 * Defines routing paths
 *
 * @author Noah Trimble
 */
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'appointment/index', pathMatch: 'full' },
  { path: 'technician/index', component: TechnicianListComponent },
  { path: 'technician/add', component: ModifyTechnicianComponent },
  { path: 'technician/edit/:id', component: ModifyTechnicianComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
