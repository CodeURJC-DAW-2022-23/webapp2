import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-user-favs',
  templateUrl: './my-user-favs.component.html',
  styleUrls: ['./my-user-favs.component.css']
})
export class MyUserFavsComponent implements OnInit{
  constructor(private userService:UserService){}
  
  ngOnInit(): void {
      this.userService.getFavs().subscribe(
        response=>{
          console.log(response);
        }
      );
  }

}
