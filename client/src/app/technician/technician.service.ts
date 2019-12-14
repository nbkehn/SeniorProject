/**
 * Defines REST API calls make for a technician
 *
 * @package technician
 * @author Noah Trimble
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/technicians';

  /**
   * Constructor for TechnicianService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a technician with a given ID
   * @param id ID of the technician
   * @return An `Observable` of the response body as a JSON object.
   */
  getTechnician(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a technician
   * @param technician Technician to be created
   * @return newly created technician
   */
  createTechnician(technician: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, technician);
  }

  /**
   * Updates a technician
   * @param id  ID of the technician to update
   * @param value Updated date for the technician
   * @return technician with updated data
   */
  updateTechnician(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  /**
   * Deletes a technician with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the technician
   */
  deleteTechnician(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all technicians
   * @return list of technicians
   */
  getTechniciansList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
