/**
 * Defines a reminder error class with it's necessary components
 *
 * @package reminder error
 * @author Noah Trimble
 */
export class ReminderError {
  id: number;
  appointmentId: number;
  reminderId: number;
  errorMessage: string;
  sent: boolean;
}
