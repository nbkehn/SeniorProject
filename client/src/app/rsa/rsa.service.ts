/**
 * Defines REST API calls make for a rsa
 *
 * @package rsa
 * @author Noah Trimble
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RsaService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/rsas';

  /**
   * Constructor for RSAService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a rsa with a given ID
   * @param id ID of the rsa
   * @return An `Observable` of the response body as a JSON object.
   */
  getRSA(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a rsa
   * @param rsa RSA to be created
   * @return newly created rsa
   */
  createRSA(rsa: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, rsa);
  }

  /**
   * Updates a rsa
   * @param id  ID of the rsa to update
   * @param value Updated date for the rsa
   * @return rsa with updated data
   */
  updateRSA(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  /**
   * Deletes a rsa with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the rsa
   */
  deleteRSA(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all rsas
   * @return list of rsas
   */
  getRSAsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
