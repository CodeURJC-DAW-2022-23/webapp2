import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  username?: string;
  u?:User;

  constructor(private userService:UserService, private authService:AuthService, private router: Router) { }

  ngOnInit(): void {
    window.addEventListener('DOMContentLoaded', event => {
      const datatablesSimple = document.getElementById('datatablesSimple') as HTMLTableElement;
      if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
      }
    });

    this.loadUser();
  }

  loadUser(){
    this.userService.getMe().subscribe(
      response=>{
        this.u = response;
        this.username=this.u.username;
        if(!(this.u.rol === "ADMIN"))
          this.router.navigate(['']);
      },
      error => {
        this.router.navigate(['']);
      }
    );
  }

  logout(){
    this.authService.logOut();
    this.router.navigate(['']);
  }
}


