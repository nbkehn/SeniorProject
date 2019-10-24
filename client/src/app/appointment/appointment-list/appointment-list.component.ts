/**
 * Angular component for viewing appointments
 *
 * @package appointment
 * @author Connor J. Parke
 */
import { Observable } from "rxjs";
import { AppointmentService } from "../appointment.service";
import { Appointment } from "../appointment";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {AlertService} from "../../alert/alert.service";
import {NgxSmartModalService} from "ngx-smart-modal";

@Component({
  selector: "app-appointment-list",
  templateUrl: "./appointment-list.component.html"
})
export class AppointmentListComponent implements OnInit {
  // the list of appointments
  appointments: Observable<Appointment[]>;
  // customers
  customers;
  // technicians
  technicians;
  // rsas
  rsas;


  /**
   * Constructor for the AppointmentListComponent, doesn't really do anything right now
   * @param appointmentService the service for the appointment component
   * @param router the router to route the appointments to each page
   * @param alertService serivce used to add alert messages
   * @param ngxSmartModalService service used to create modals
   */
  constructor(private appointmentService: AppointmentService,
              private router: Router,
              private alertService: AlertService,
              private ngxSmartModalService: NgxSmartModalService) {}

  /**
   * reloads the data on initialize of the page to ensure that the page has the most updated details
   */
  ngOnInit() {
    this.reloadData();
    this.customers = [];
    this.technicians = [];
    this.rsas = [];
    this.setCustomers();
    this.setTechnicians();
    this.setRSAs();
  }

  /**
   * reloads the data with the most updated list of appointments from the database
   * calls from the appointment service so that it makes the DB call
   */
  reloadData() {
    this.appointments = this.appointmentService.getAppointmentsList();
  }

  /**
   * Set customers
   */
  setCustomers() {
    this.appointmentService.getCustomers()
      .subscribe(
        data => {
          this.customers = data;
        },
        error => {
          this.alertService.error('Customers could not be loaded.', false);
        });
  }

  /**
   * Set technicians
   */
  setTechnicians() {
    this.appointmentService.getTechnicians()
      .subscribe(
        data => {
          this.technicians = data;
        },
        error => {
          this.alertService.error('Technicians could not be loaded.', false);
        });
  }

    /**
   * Set rsas
   */
  setRSAs() {
    this.appointmentService.getRSAs()
      .subscribe(
        data => {
          this.rsas = data;
        },
        error => {
          this.alertService.error('RSAs could not be loaded.', false);
        });
  }

  /**
   * deletes the appointment from the database and prints the response to the console
   * @param id the id of the appointment to delete
   */
  deleteAppointment(id: number) {
    this.appointmentService.deleteAppointment(id)
      .subscribe(
        data => {
          this.alertService.success('Appointment was deleted successfully.', false);
          this.reloadData();
        },
        error => {
          this.alertService.error('Appointment could not be deleted.', false);
        });
  }

  /**
   * reroutes the page to the edit page with the id passed in through the router
   * @param id the id of the appointment to edit
   */
  editAppointment(id: number) {
    this.router.navigate(['/appointment/edit', id]);
  }

  /**
   * Translate customer option id to value
   * @param id Option id
   * @return translated customer
   */
  translateCustomer(id: number) {
    return this.customers[id];
  }

  /**
   * Translate technician option id to value
   * @param id Option id
   * @return translated technician
   */
  translateTechnician(id: number) {
    return this.technicians[id];
  }

  /**
   * Translate rsa option id to value
   * @param id Option id
   * @return translated rsa
   */
  translateRSA(id: number) {
    return this.rsas[id];
  }
}
