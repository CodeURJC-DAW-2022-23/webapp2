import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Aso } from '../models/aso.model';
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

  editMyAsso(asso: Aso) {
    console.log(BASE_URL + '/' + 1);
    this.http.put(BASE_URL + '/' + 1, JSON.stringify(asso)).pipe(); //BASE_URL + '/miAsociacion'
  }
  
  private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}
