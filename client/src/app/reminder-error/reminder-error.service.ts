/**
 * Defines REST API calls for a reminder error
 *
 * @package reminder-error
 * @author Noah Trimble
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReminderErrorService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/reminderErrors';

  /**
   * Constructor for ReminderErrorService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a list of all reminder errors
   * @return list of reminder errors
   */
  getReminderErrorsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  /**
   * Resend errored notifications
   * @return response
   */
  resend(): Observable<any> {
    return this.http.post(`${this.baseUrl}/resend`, []);
  }
}
