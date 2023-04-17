import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { UserProfile } from '../models/user.rest.model';

import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

const BASE_URL = '/api/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient ) { }

  getMe(): Observable<UserProfile>{
    return this.http.get(BASE_URL+'/me', { withCredentials: true }).pipe()as Observable<UserProfile>;
  }

  register(formData: FormData){
    return this.http.post(BASE_URL + "/", formData).pipe(
      map((response: any) => {
        return response;
      }),
      catchError((error: any) => {
        return throwError('Algo salio mal');
      })
    );
  }

}
