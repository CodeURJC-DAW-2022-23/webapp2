import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import {Router} from "@angular/router"


@Component({
  selector: 'app-edit-my-user',
  templateUrl: './edit-my-user.component.html',
  styleUrls: ['./edit-my-user.component.css']
})
export class EditMyUserComponent implements OnInit {
  user?:User;
  username = this.user?.username;
  email = this.user?.email;

  constructor(private userService:UserService,private router: Router){
    
  }

  ngOnInit() {
    this.loadUser();
  }

  loadUser(){
    this.userService.getMe().subscribe(
      response=>{
        this.user = response;
        this.username=this.user.username;
        this.email=this.user.email;
      }
    );
    //this.username = this.u;
  }

  onClickSubmit() {
    if(this.username != undefined && this.email != undefined){
      this.userService.editUser(this.username,this.email).subscribe(
        response =>{
          this.router.navigate(['/login'])
        }
      );
      
    }
  }
}
