import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor() { }
  
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
}
