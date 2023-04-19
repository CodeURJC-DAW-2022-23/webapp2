import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './user.service';
import { User } from '../models/user.model';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

const BASE_URL = '/api/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  logged!: boolean;
  user: User | undefined;

  constructor(private http: HttpClient, private router: Router, private userService: UserService) {
    this.reqIsLogged();
  }

  reqIsLogged(){
    this.userService.getMe().subscribe(
      response=>{
        this.user = response.user;
        this.logged = true;
      },
      error => console.log(error)
    );
  }

  logIn(username:string, password:string){
    this.http.post(BASE_URL+'/login', { username: username, password: password }, { withCredentials: true }).subscribe(
      (response)=> this.reqIsLogged(),
      (error) => alert("Credenciales invalidas")
    );
    // .pipe(
    //   map((response:any)=>{

    //     console.log(response);

    //     return response;
    //   }),
    //   catchError((error: HttpErrorResponse) => {
    //     let errorMessage = 'Ocurrió un error al intentar iniciar sesión';
    //     if (error.status === 401) {
    //       errorMessage = 'Credenciales inválidas';
    //     }
    //     return throwError(errorMessage);
    //   })
    // )
  }

  logOut(){
    return this.http.post(BASE_URL + '/logout', { withCredentials: true }).subscribe((resp: any)=>{
      this.logged = false;
      this.user = undefined;
      this.router.navigate(['/']);
    })
  }
}
