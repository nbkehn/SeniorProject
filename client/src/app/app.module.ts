/**
 * Angular module dependencies
 *
 * @author Noah Trimble
 */

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { AppComponent } from './app.component';
import { ModifyTechnicianComponent } from './technician/modify-technician/modify-technician.component';
import { TechnicianListComponent } from './technician/technician-list/technician-list.component';
import { HttpClientModule } from '@angular/common/http';

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
    MustMatchDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxSmartModalModule.forRoot(),
    NgbModule
  ],
  providers: [
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
