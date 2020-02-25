/**
 * Defines REST API calls make for a template
 *
 * @package template
 * @author Noah Trimble
 * @author Soumya Bagade
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TemplateService {
  // Base URL for each request
  private baseUrl = 'http://localhost:8080/api/v1/templates';

  /**
   * Constructor for TemplateService
   * @param http HTTP client used to make API calls
   */
  constructor(private http: HttpClient) { }

  /**
   * Gets a template with a given ID
   * @param id ID of the template
   * @return An `Observable` of the response body as a JSON object.
   */
  getTemplate(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  /**
   * Creates a template
   * @param template Template to be created
   * @return newly created template
   */
  createTemplate(template: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, template);
  }

  /**
   * Updates a template
   * @param id  ID of the template to update
   * @param value Updated date for the template
   * @return template with updated data
   */
  updateTemplate(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  /**
   * Deletes a template with a given ID
   * @param id ID of the tech to delete
   * @return response detailing result of deleting the template
   */
  deleteTemplate(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  /**
   * Gets a list of all templates
   * @return list of templates
   */
  getTemplatesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  /**
   * Gets a list of all template variables
   * @return list of template variables
   */
  getTemplateVariables(): Observable<any> {
    return this.http.get(`${this.baseUrl}/variables`);
  }
}
