/**
 * Defines REST API calls make for a assignment
 *
 * @package assignment
 * @author Connor J. Parke
 * @author Renee Segda
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Assignment } from './assignment';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/assignments';

  /**
   * Constructor for AssignmentService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a assignment with a given ID
   * @param id ID of the assignment
   * @return An `Observable` of the response body as a JSON object.
   */
  getAssignment(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a assignment
   * @param assignment Assignment to be created
   * @return newly created assignment
   */
  createAssignment(assignment: Assignment): Observable<any> {
    return this.http.post(`${this.baseUrl}`, assignment);
  }

  /**
   * Updates a assignment
   * @param id  ID of the assignment to update
   * @param value Updated date for the assignment
   * @return assignment with updated data
   */
  updateAssignment(id: number, assignment: Assignment): Observable<object> {
    return this.http.put(`${this.baseUrl}/${id}`, assignment);
  }

  /**
   * Deletes a assignment with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the assignment
   */
  deleteAssignment(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all assignments
   * @return list of assignments
   */
  getAssignmentsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}