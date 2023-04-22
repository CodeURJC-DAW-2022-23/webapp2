import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {
  id?: number;
  username?: string;
  email?: string;
  rol?: string;
  user: User | undefined;
  users: User[];

  constructor(private router: Router, private userService: UserService) {
    this.userService.allUsers().subscribe((response)=>{
      this.users=response;
    })
   }

  modify() {
     this.router.navigate(['/admin/edituser/', this.user?.id]);
  }
}
