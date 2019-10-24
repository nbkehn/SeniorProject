/**
 * Defines a appointment class with it's necessary components
 *
 * @package appointment
 * @author Connor J. Parke
 */
export class Appointment {
  id: number;
  startDateTime: Date;
  endDateTime: Date;
  customer: Customer;
  technicians: Technician[];
  rsa: RSA;
}
