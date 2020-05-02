import { Customer } from '../customer/customer';

/**
 * Defines a flooring class with it's necessary components
 *
 * @package flooring
 * @author Noah Trimble, Will Duke
 */
export class Flooring {
  id: number;
  name: string;
  style: string;
  color: string;
  company: string;
  checkedOut: boolean;
  checkedTo: Customer;
  hashcode: String;
}
