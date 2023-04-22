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

  allUsers():Observable<any>{
    return this.http.get(BASE_URL+"/all").pipe() as Observable<User[]>;
  }

   deleteUser(id: number) {
   // return this.httpClient.delete(BASE_URL + '/' + id, { withCredentials: true });
    return this.http.delete(BASE_URL + '/' + id, { withCredentials: true });
  }

}