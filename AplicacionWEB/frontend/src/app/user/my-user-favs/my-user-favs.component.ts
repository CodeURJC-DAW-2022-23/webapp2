import { Component, OnInit } from '@angular/core';
import { Event } from 'src/app/models/event.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-user-favs',
  templateUrl: './my-user-favs.component.html',
  styleUrls: ['./my-user-favs.component.css']
})
export class MyUserFavsComponent implements OnInit{
  events: Event[];

  constructor(private userService:UserService){}
  
  removeFav(id:Number){
    this.userService.removeFav(id).subscribe(
      response =>{
        console.log(response);
        this.getFavs();
      }
    )
  }

  ngOnInit(): void {
    this.getFavs();
  }

  getFavs(){
    this.userService.getFavs().subscribe(
      response=>{
        this.events = response;
        for (let i = 0; i < response.length; i++) {
          console.log(this.events[i]);

        }
      }
    );
  }

}
