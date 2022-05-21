import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const API_URL = 'http://localhost:8090/api/organisme/';
const headers = { 'Authorization': 'bearer'+window.sessionStorage.getItem("auth-token") , responseType: 'json'};
@Injectable({
  providedIn: 'root'
})
export class OrganismeService {

  constructor(private http: HttpClient) { }
  
  getOrganisme(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'json' });
  }

  addOrganisme(data : any): Observable<any> {
    return this.http.post<any>(API_URL + 'add', data, { headers });
  }

  updateOrganisme(data : any): Observable<any> {
    return this.http.put<any>(API_URL + 'update', data, { headers });
  }

  deleteOrganisme(id : number): Observable<any> {
    return this.http.delete<any>(API_URL + 'delete/'+id, { headers });
  }
}
