import { Customer } from '../customer/customer';
import { Technician } from '../technician/technician';
import { Rsa } from '../rsa/rsa';
import { Flooring } from '../flooring/flooring';


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
  rsa: Rsa;
  flooring: Flooring;

  compareAppointments(appointment: Appointment) {
    if (this.id > appointment.id)
      return this;
    return appointment;
  }
}

