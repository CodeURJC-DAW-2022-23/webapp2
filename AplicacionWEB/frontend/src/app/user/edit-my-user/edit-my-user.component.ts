import { Component,Input } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-edit-my-user',
  templateUrl: './edit-my-user.component.html',
  styleUrls: ['./edit-my-user.component.css']
})
export class EditMyUserComponent {
  user?:User;
  username = this.user?.username;
  email = this.user?.email;
  userId = this.user?.id;

  constructor(private userService:UserService){
    
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
        this.userId=this.user.id;
      }
    );
    //this.username = this.u;
  }
}
