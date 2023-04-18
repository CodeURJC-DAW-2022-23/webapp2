import { Injectable } from '@angular/core';
import { AsoRest } from '../models/aso.rest.model';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';

const BASE_URL = '/api/aso';
@Injectable({
  providedIn: 'root'
})
export class AsoService {

  constructor(private http: HttpClient) { }
  asoList(): Observable<AsoRest[]>{
    return this.http.get(BASE_URL+'/asociationsList').pipe() as Observable<AsoRest[]>;
  }
}
