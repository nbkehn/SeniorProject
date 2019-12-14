/**
 * Defines REST API calls make for a user
 *
 * @package user
 * @author Noah Trimble
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/users';

  /**
   * Constructor for UserService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a user with a given ID
   * @param id ID of the user
   * @return An `Observable` of the response body as a JSON object.
   */
  getUser(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a user
   * @param user User to be created
   * @return newly created user
   */
  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  /**
   * Updates a user
   * @param id  ID of the user to update
   * @param value Updated date for the user
   * @return user with updated data
   */
  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  /**
   * Deletes a user with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the user
   */
  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all users
   * @return list of users
   */
  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
