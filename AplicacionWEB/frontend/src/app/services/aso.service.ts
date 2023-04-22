import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Aso } from '../models/aso.model';
import { Event } from '../models/event.model';
import { catchError, throwError } from 'rxjs';

const BASE_URL = '/api/aso';
@Injectable({
  providedIn: 'root'
})
export class AsoService {

  constructor(private http: HttpClient) { }
  asoList(): Observable<Aso[]> {
    return this.http.get(BASE_URL+'/asociationsList').pipe() as Observable<Aso[]>;
  }

  getAssoById(id: Number) : Observable<Aso> {
    return this.http.get(BASE_URL + '/' + id).pipe() as Observable<Aso>;
  }

  getMyAsso() : Observable<Aso> {
    return this.http.get(BASE_URL + '/miAsociacion').pipe() as Observable<Aso>;
  }

  getEvents() : Observable<Event[]> {
    return this.http.get(BASE_URL + '/misEventos').pipe() as Observable<Event[]>;
  }

  editMyAsso(asso: Aso) {
    console.log(BASE_URL + '/' + 1);
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.put(BASE_URL + '/miAsociacion', JSON.stringify(asso),{headers}).pipe();
  }
  
  private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
  }
  
  deleteAso(id: number) {
    return this.http.delete(BASE_URL + '/' + id, { withCredentials: true });
  }

  
  adminEditAso(aso: Aso) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.put(BASE_URL + '/editAso', JSON.stringify(aso),{headers}).pipe();
  }
}
