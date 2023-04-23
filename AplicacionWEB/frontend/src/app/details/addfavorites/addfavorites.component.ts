import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { Event } from 'src/app/models/event.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-addfavorites',
  templateUrl: './addfavorites.component.html',
  styleUrls: ['./addfavorites.component.css']
})
export class FavoritesComponent {
  constructor(private authService:AuthService, private router: Router) { }
  @Input()
  eventF:Event;
  ngOnInit() {
   
  }
  agregarFavoritos(){
    if(this.authService.logged){

    }else{
      this.router.navigate(['/login']);
    }
  }
}
