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
  username:string;
  constructor(private router: Router, activatedRoute: ActivatedRoute,private userService: UserService){
    const idEvent = activatedRoute.snapshot.params['id'] as number;
    this.load(idEvent);
    //console.log(this.user.username);
  }
  
  

  load(id: number){
    this.userService.getUser(id).subscribe((response)=>{ 
      this.user = response;
      this.username = response.username;
    });
  }
  ngOnInit() {
    
  }
  //, user : any
  editUser(id: number) {
    //user.preventDefault();
    this.userService.adminEditUser(id, this.user).subscribe(response=>{
      this.router.navigate(['admin/users']);
    });
  }
}
