import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { Event } from 'src/app/models/event.model';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-addfavorites',
  templateUrl: './addfavorites.component.html',
  styleUrls: ['./addfavorites.component.css']
})
export class FavoritesComponent {
  constructor(private authService:AuthService, private router: Router, private userService:UserService) { }
  @Input()
  eventF:Event;
  ngOnInit() {
   
  }
  agregarFavoritos(){
    if(this.authService.logged){
      this.userService.addFavorites(this.eventF.id).subscribe();
    }else{
      this.router.navigate(['/login']);
    }
  }
}
