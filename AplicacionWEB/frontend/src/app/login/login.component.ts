import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { User } from '../models/user.model';

import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { Event } from '../models/event.model';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!:string;
  rol:string = "BASE";
  email!:string;

  constructor(private authService: AuthService, private userService:UserService) {
    
  }
  
  ngOnInit() {
    $("#goRight").on("click", function () {
      $("#slideBox").animate({
        marginLeft: "0"
      });
      $(".topLayer").animate({
        marginLeft: "100%"
      });
    });
    
    $("#goLeft").on("click", function () {
      if (window.innerWidth > 769) {
        $("#slideBox").animate({
          marginLeft: "50%"
        });
      } else {
        $("#slideBox").animate({
          marginLeft: "20%"
        });
      }
      $(".topLayer").animate({
        marginLeft: "0"
      });
    });
  }

  logIn(){
    this.authService.logIn(this.username, this.password);
    
  }

  register(){
    console.log(this.rol);
    this.userService.register(this.username,this.password,this.rol,this.email).subscribe(
      response =>{
        console.log(response)
      }
    );
  }
}
