import { Component, OnInit  } from '@angular/core';
import { faBars, faUser } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../services/auth.service';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit  {
  u?:User;
  faBars = faBars;
  faUser = faUser;
  username?:string;
  showme:boolean=true;
  

  constructor(private userService:UserService){
    
  }

  ngOnInit() {
    this.loadUser();
  }

  loadUser(){
    this.userService.getMe().subscribe(
      response=>{
        this.u = response;
        this.username=this.u.username;
      }
    );
    //this.username = this.u;
  }

  toogleSidebar(){
    this.showme = !this.showme
  }
}
