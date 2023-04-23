import { Component, OnChanges, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnChanges{
  currentUser:User;

  constructor(private userService:UserService){}

  ngOnChanges(): void {
    this.userService.getMe().subscribe(
      response=>{
        this.currentUser = response;
      } 
    );
  }
}
