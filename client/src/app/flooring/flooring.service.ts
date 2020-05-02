/**
 * Defines REST API calls make for flooring
 *
 * @package flooring
 * @author Noah Trimble, Will Duke
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlooringService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/flooringtype';

  /**
   * Constructor for FlooringService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets flooring with a given ID
   * @param id ID of the flooring
   * @return An `Observable` of the response body as a JSON object.
   */
  getFlooring(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getFlooringQR(id : number): Observable<any> {
    return this.http.get(`${this.baseUrl}/createqr/${id}`);
  }

  /**
   * Gets flooring with a given hash code
   * @param hashCode hash code of the flooring information
   * @return An observable of the response body as a JSON object. 
   */
getFlooringbyHash(hashCode: string): Observable<any> {
  return this.http.get(`${this.baseUrl}/qrcode/${hashCode}`)
}
  /**
   * Creates a flooring
   * @param flooring Flooring to be created
   * @return newly created flooring
   */
  createFlooring(flooring: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, flooring);
  }

  /**
   * Upload sample file
   * @param id
   * @param name 
   */
  uploadSampleFile(formData : FormData): Observable<Object> {
    
    return this.http.post(`${this.baseUrl}/upload`, formData);
  }

  /**
   * Updates a specific flooring
   * @param id  ID of the flooring to update
   * @param name Updated name for the flooring
   * @return flooring with updated data
   */
  updateFlooring(id: number, name: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, name);
  }

  /**
   * Deletes a flooring with a given ID
   * @param id ID of the flooring to delete
   * @return response detailing result of deleting the flooring
   */
  deleteFlooring(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all floorings 
   * @return list of floorings
   */
  getFlooringsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
