import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { EventService } from './event.service';
import { Event } from '../models/event.model';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

const BASE_URL = '/api/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  logged!: boolean;

  constructor(private http: HttpClient, private router: Router) {
   
  }

  logOut() {
    
  }
}
