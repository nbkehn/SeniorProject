import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { AppComponent } from './app.component';
import { CreateTechnicianComponent } from './create-technician/create-technician.component';
import { TechnicianDetailsComponent } from './technician-details/technician-details.component';
import { TechnicianListComponent } from './technician-list/technician-list.component';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    CreateTechnicianComponent,
    TechnicianDetailsComponent,
    TechnicianListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
