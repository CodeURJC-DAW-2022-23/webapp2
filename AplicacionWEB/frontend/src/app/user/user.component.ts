import { Component, OnInit  } from '@angular/core';
import { faBars, faUser,faGaugeHigh, faStar } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../services/auth.service';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit  {
  u?:User;
  faGauge=faStar;
  faBars = faBars;
  faUser = faUser;
  username?:string;
  showme:boolean=true;

  constructor(private userService:UserService, private authService:AuthService,private router: Router){
    
  }

  ngOnInit() {
    this.loadUser();
  }

  loadUser(){
    this.userService.getMe().subscribe(
      response=>{
        this.u = response;
        this.username=this.u.username;
        if(!(this.u.rol === "BASE"))
          this.router.navigate(['']);
      },
      error => {
        this.router.navigate(['']);
      }
    );
    
  }

  logout(){
    this.authService.logOut();
    this.router.navigate(['']);
  }

  toogleSidebar(){
    this.showme = !this.showme
  }
}
