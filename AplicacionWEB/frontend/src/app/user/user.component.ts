import { Component, OnInit  } from '@angular/core';
import { faBars, faUser } from '@fortawesome/free-solid-svg-icons';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { UserProfile } from '../models/user.rest.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit  {
  u?:UserProfile;
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
        console.log(response);
        this.u = response;
        console.log(this.u);
        this.username=this.u.user.username;
      }
    );
    //this.username = this.u;
  }

  toogleSidebar(){
    this.showme = !this.showme
  }
}
