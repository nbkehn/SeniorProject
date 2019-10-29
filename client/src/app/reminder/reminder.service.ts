/**
 * Defines REST API calls make for a reminder
 *
 * @package reminder
 * @author Noah Trimble
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReminderService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/reminders';

  /**
   * Constructor for ReminderService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a reminder with a given ID
   * @param id ID of the reminder
   * @return An `Observable` of the response body as a JSON object.
   */
  getReminder(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a reminder
   * @param reminder Reminder to be created
   * @return newly created reminder
   */
  createReminder(reminder: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, reminder);
  }

  /**
   * Updates a reminder
   * @param id  ID of the reminder to update
   * @param value Updated date for the reminder
   * @return reminder with updated data
   */
  updateReminder(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  /**
   * Deletes a reminder with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the reminder
   */
  deleteReminder(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all reminders
   * @return list of reminders
   */
  getRemindersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  /**
   * Get possible times to send
   * @return times to send
   */
  getTimesToSend() {
    return this.http.get(`${this.baseUrl}/timeToSend`);
  }

  /**
   * Get templates
   * @return templates
   */
  getTemplates() {
    return this.http.get(`${this.baseUrl}/template`);
  }
}
