import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!:string;
  rol!:string;
  email!:string;

  constructor(private authService: AuthService, private userService:UserService) { }
  
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
    const formData = new FormData();
    formData.append('name', this.username);
    formData.append('email', this.email);
    formData.append('password', this.password);
    formData.append('rol', this.rol);

    this.userService.register(formData);
  }
}
