import { Component } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent {

  user: User;
  
  constructor(private router: Router, activatedRoute: ActivatedRoute,private userService: UserService){
    const idEvent = activatedRoute.snapshot.params['id'] as number;
    this.load(idEvent);
  }
  
  

  load(id: number){
    this.userService.getUser(id).subscribe((response)=>{ 
      this.user = response;
    });
  }
  ngOnInit() {
    
  }
  
  editUser(id: number) {
    this.userService.adminEditUser(id, this.user).subscribe(response=>{
      this.router.navigate(['admin/users']);
    });
  }
}
