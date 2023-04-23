import { Component } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent {

  user: User;

  constructor(private userService: UserService){}

  ngOnInit(id: Number) {
    this.userService.getMe().subscribe((response)=>{ 
      this.user = response;
    });
  }
  editUser(id: number, user : any) {
    user.preventDefault();
    this.userService.adminEditUser(id, this.user).subscribe(response=>{});
  }
}
