import { Customer } from '../customer/customer';
import { Technician } from '../technician/technician';
import { Rsa } from '../rsa/rsa';
import { Flooring } from '../flooring/flooring';
import { Assignment } from '../assignment/assignment';


/**
 * Defines a appointment class with it's necessary components
 *
 * @package appointment
 * @author Connor J. Parke
 */
export class Appointment {
  id: number;
  startDate: Date;
  endDate: Date;
  customer: Customer;
  technicians: Technician[];
  assignments: Assignment [];
  rsa: Rsa;
  flooring: Flooring;

  // constructor() {
  //   this.technicians = [];
  //   this.assignments = [];
  // }

  compareAppointments(appointment: Appointment) {
    if (this.id > appointment.id)
      return this;
    return appointment;
  }
}

