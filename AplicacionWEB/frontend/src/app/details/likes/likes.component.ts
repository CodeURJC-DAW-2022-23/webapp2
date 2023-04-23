import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/app/models/event.model';
import { AuthService } from 'src/app/services/auth.service';
import { faBars, faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-likes',
  templateUrl: './likes.component.html',
  styleUrls: ['./likes.component.css']
})
export class LikesComponent {
  constructor(private authService:AuthService, private router: Router){}
  faBars = faBars;
  faUser = faUser;
 
  @Input()
  eventL:Event;

 likeStyle:string="bx-happy";
 dislikeStyle:string="bx-sad";
 like(){
  if(this.authService.logged){
    this.likeStyle="bxs-happy";
  }else{
    this.router.navigate(['/login']);
  }
  
 }
 dislike(){
  if(this.authService.logged){
    this.dislikeStyle="bxs-sad";
  }else{
    this.router.navigate(['/login']);
  }
 }
}
