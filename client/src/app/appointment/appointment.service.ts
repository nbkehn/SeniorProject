/**
 * Defines REST API calls make for a appointment
 *
 * @package appointment
 * @author Connor J. Parke
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from './appointment';
@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/appointments';

  /**
   * Constructor for AppointmentService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a appointment with a given ID
   * @param id ID of the appointment
   * @return An `Observable` of the response body as a JSON object.
   */
  getAppointment(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a appointment
   * @param appointment Appointment to be created
   * @return newly created appointment
   */
  createAppointment(appointment: Appointment): Observable<object> {
    return this.http.post(`${this.baseUrl}`, appointment);
  }

  /**
   * Updates a appointment
   * @param id  ID of the appointment to update
   * @param value Updated date for the appointment
   * @return appointment with updated data
   */
  updateAppointment(id: number, appointment: Appointment): Observable<object> {
    return this.http.put(`${this.baseUrl}/${id}`, appointment);
  }

  /**
   * Deletes a appointment with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the appointment
   */
  deleteAppointment(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all appointments
   * @return list of appointments
   */
  getAppointmentsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  /**
   * Send OTW Message
   * @param appointment Appointment to send OTW message for
   * @return whether appointment was sent or not
   */
  sendOTWMessage(appointment: Appointment): Observable<any> {
    return this.http.post(`${this.baseUrl}/sendOTW`, appointment);
  }
}
