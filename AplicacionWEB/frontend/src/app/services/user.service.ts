import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { User } from '../models/user.model';
import { Event } from '../models/event.model';

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

  editUser(name:string,email:string): Observable<User>{
    /*let params = new HttpParams();
    params = params.append("newName", name);
    params = params.append("newEmail", email);*/
    return this.http.patch(BASE_URL + "/me?newName="+name+"&newEmail="+email,{ withCredentials: true }).pipe()as Observable<User>;
  }

  getFavs(){
    return this.http.get(BASE_URL +"/me/favorites").pipe()as Observable<Event[]>;
  }

  removeFav(id:Number){
    return this.http.delete(BASE_URL +"/me/favorites/"+id);
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
