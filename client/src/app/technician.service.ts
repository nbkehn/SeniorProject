import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {

  private baseUrl = 'http://localhost:8080/api/v1/technicians';

  constructor(private http: HttpClient) { }

  getTechnician(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createTechnician(technician: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, technician);
  }

  updateTechnician(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteTechnician(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getTechniciansList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
