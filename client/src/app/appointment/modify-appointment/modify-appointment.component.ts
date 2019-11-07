/**
 * Angular component for creating and editing appointments
 *
 * @package appointment
 * @author Connor J. Parke
 */
import { AppointmentService } from '../appointment.service';
import { Appointment } from '../appointment';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService } from '../../alert/alert.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { TechnicianService } from 'src/app/technician/technician.service';
import { RsaService } from 'src/app/rsa/rsa.service';
import { FlooringService } from 'src/app/flooring/flooring.service';

import { Customer } from 'src/app/customer/customer';
import { Rsa } from 'src/app/rsa/rsa';
import { Technician } from 'src/app/technician/technician';
import { Flooring } from 'src/app/flooring/flooring';

@Component({
  selector: 'app-modify-appointment',
  templateUrl: './modify-appointment.component.html'
})
export class ModifyAppointmentComponent implements OnInit {
  // the appointment's ID in the database
  id: number;
  // the appointment object to create and store data into
  appointment: Appointment;
  // the title for the page
  title: string;
  // the list of customers
  customerOptions: Customer[];
  // the list of technicians
  technicianOptions: Technician[];
  // the list of rsas
  rsaOptions: Rsa[];
  // this list of flooring options
  flooringOptions: Flooring[];

  /**
   * Creates the instance of the component
   * @param route
   * @param appointmentService
   * @param router
   * @param alertService
   */
  constructor(private route: ActivatedRoute,
              private appointmentService: AppointmentService,
              private customerService: CustomerService,
              private technicianService: TechnicianService,
              private rsaService: RsaService,
              private flooringService: FlooringService,
              private router: Router,
              private alertService: AlertService) { }

  /**
   * initializes the components and populates the form with appointment data if it is being edited (instead of created)
   */
  ngOnInit() {
    // initialize mapped options
    this.customerOptions = [];
    this.technicianOptions = [];
    this.rsaOptions = [];
    this.flooringOptions = [];
    this.setCustomers();
    this.setTechnicians();
    this.setRSAs();
    this.setFloorings();

    // initializes a new appointment
    this.appointment = new Appointment();
    this.appointment.customer = new Customer();
    this.appointment.technicians = [];
    this.appointment.rsa = new Rsa();
    this.appointment.flooring = new Flooring();

    // gets the id from the routing
    this.id = this.route.snapshot.params['id'];

    // changes the title depending on whether the appointment has been stored in the database and is now being edited (Edit Appointment) or created as a new one (Create Appointment)
    this.title = this.id ? 'Edit Appointment' : 'Create Appointment';

    // if the id is not null, it means that the appointment has already been stored and is now being edited.
    // tries to get the appointment from the database and logs whether the appointment could be retrieved from the database in the console
    if (this.id) {
      this.appointmentService.getAppointment(this.id)
        .subscribe(data => {
          this.appointment = data;
        },
        error => {
          this.alertService.error('Appointment could not be loaded.', false);
        });
    }
  }

  technicianCompare(technician1: Technician, technician2: Technician) {
    return technician1 && technician2 ? technician1.id === technician2.id : technician1 === technician2;
  }

  /**
   * Set customers
   */
  setCustomers() {
    this.customerService.getCustomersList()
      .subscribe(
        data => {
          this.customerOptions = data;
        },
        error => {
          this.alertService.error('Customers could not be loaded.', false);
        });
  }

  /**
   * Set technicians
   */
  setTechnicians() {
    this.technicianService.getTechniciansList()
      .subscribe(
        data => {
          this.technicianOptions = data;
        },
        error => {
          this.alertService.error('Technicians could not be loaded.', false);
        });
  }

  /**
   * Set RSAs
   */
  setRSAs() {
    this.rsaService.getRSAsList()
      .subscribe(
        data => {
          this.rsaOptions = data;
        },
        error => {
          this.alertService.error('RSAs could not be loaded.', false);
        });
  }

  /**
   * Set Flooring
   */
  setFloorings() {
    this.flooringService.getFlooringsList()
      .subscribe(
        data => {
          this.flooringOptions = data;
        },
        error => {
          this.alertService.error('Floorings could not be loaded.', false);
        });
  }

  /**
   * saves the techician to the database and logs the response code (200 OK or 4xx error) to the console
   * the console can be accessed in the web page by pressing Fn + F12 on a Windows system
   */
  save() {
    // saves the appointment object to the database -- if the appointment hasn't been created before, it saves as a new entry
    // if the appointment has been created before, it updates the appointment
    let response = !this.id ? this.appointmentService.createAppointment(this.appointment)
      : this.appointmentService.updateAppointment(this.id, this.appointment);
    response.subscribe(
      data => {
        // Display success message and go back to list
        this.alertService.success('Appointment saved successfully.', true);
        this.gotoList();
      },
      error => {
        // Display error message on error and remain in form
        this.alertService.error('The appointment could not be saved.', false);
      });
  }

  /**
   * This method is called on submit.
   * Calls on the save method to save the entry to the database.
   */
  onSubmit() {
    this.save();
  }

  /**
   * Resets the page back to the appointments list instead of the add appointment page
   */
  gotoList() {
    this.router.navigate(['/appointment/index']);
  }
}
