import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8090/api/session/';
const headers = { 'Authorization': 'bearer'+window.sessionStorage.getItem("auth-token") , responseType: 'json'};

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) { }

  getSessions(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'json' });
  }

  addSession(data : any): Observable<any> {
    return this.http.post<any>(API_URL + 'add', data, { headers });
  }

  updateSession(data : any): Observable<any> {
    return this.http.put<any>(API_URL + 'update', data, { headers });
  }

  deleteSession(id : number): Observable<any> {
    return this.http.delete<any>(API_URL + 'delete/'+id, { headers });
  }
}
