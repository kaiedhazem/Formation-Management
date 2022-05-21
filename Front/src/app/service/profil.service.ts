import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const API_URL = 'http://localhost:8090/api/profil/';
const headers = { 'Authorization': 'bearer'+window.sessionStorage.getItem("auth-token") , responseType: 'json'};

@Injectable({
  providedIn: 'root'
})
export class ProfilService {

  constructor(private http: HttpClient) { }
  getProfil(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'json' });
  }

  addProfil(data : any): Observable<any> {
    return this.http.post<any>(API_URL + 'add', data, { headers });
  }

  updateProfil(data : any): Observable<any> {
    return this.http.put<any>(API_URL + 'update', data, { headers });
  }

  deleteProfil(id : number): Observable<any> {
    return this.http.delete<any>(API_URL + 'delete/'+id, { headers });
  }
}
