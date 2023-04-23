import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../models/user.model';

import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

const BASE_URL = '/api/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient ) { }

  getMe(): Observable<User>{
    return this.http.get(BASE_URL+'/me', { withCredentials: true }).pipe()as Observable<User>;
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
  addFavorites(id:number){
    const body={};
    return this.http.post(BASE_URL+'/me/favorites/'+id,body).pipe();
  }

}
