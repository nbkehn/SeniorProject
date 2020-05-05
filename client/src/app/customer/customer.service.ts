/**
 * Defines REST API calls make for a customer
 *
 * @package customer
 * @author Noah Trimble
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/customers';

  /**
   * Constructor for CustomerService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a customer with a given ID
   * @param id ID of the customer
   * @return An `Observable` of the response body as a JSON object.
   */
  getCustomer(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a customer
   * @param customer Customer to be created
   * @return newly created customer
   */
  createCustomer(customer: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}`, customer);
  }

  /**
   * Updates a customer
   * @param id  ID of the customer to update
   * @param value Updated date for the customer
   * @return customer with updated data
   */
  updateCustomer(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  /**
   * Deletes a customer with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the customer
   */
  deleteCustomer(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all customers
   * @return list of customers
   */
  getCustomersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  /**
   * Get communication preference options
   * @return communication preference options
   */
  getCommunicationPreferenceOptions() {
    return this.http.get(`${this.baseUrl}/communicationType`);
  }
}
