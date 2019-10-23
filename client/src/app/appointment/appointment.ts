/**
 * Defines a appointment class with it's necessary components
 *
 * @package appointment
 * @author Connor J. Parke
 */
export class Appointment {
  id: number;
  timeslots: Date[];
  customer: number;
  technicians: number[];
  rsa: number;
}
