import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8090/api/pays/';
const headers = { 'Authorization': 'bearer'+window.sessionStorage.getItem("auth-token") , responseType: 'json'};

@Injectable({
  providedIn: 'root'
})
export class PaysService {

  constructor(private http: HttpClient) { }
  getPays(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'json' });
  }

  addPays(data : any): Observable<any> {
    return this.http.post<any>(API_URL + 'add', data, { headers });
  }

  updatePays(data : any): Observable<any> {
    return this.http.put<any>(API_URL + 'update', data, { headers });
  }

  deletePays(id : number): Observable<any> {
    return this.http.delete<any>(API_URL + 'delete/'+id, { headers });
  }
}
