import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';

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

  constructor(private router: Router) { }

  modify() {
   //  this.router.navigate(['/admin/edituser/', this.user?.id]);
  }
}
