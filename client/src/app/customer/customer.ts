/**
 * Defines a customer class with it's necessary components
 *
 * @package customer
 * @author Noah Trimble
 */
export class Customer {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  address: string;
  communicationPreference: CommunicationPreference
}

export enum CommunicationPreference {
  Email,
  Text,
  Both
}
