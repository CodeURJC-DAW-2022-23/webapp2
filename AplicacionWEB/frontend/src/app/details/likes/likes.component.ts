import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/app/models/event.model';
import { AuthService } from 'src/app/services/auth.service';
import { faFaceLaugh as faLikeSelect, faFaceFrownOpen as faDislikeSelect} from '@fortawesome/free-solid-svg-icons';
import { faFaceLaugh as faLike, faFaceFrownOpen as faDislike} from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'app-likes',
  templateUrl: './likes.component.html',
  styleUrls: ['./likes.component.css']
})
export class LikesComponent {
  constructor(private authService:AuthService, private router: Router){}
 
  @Input()
  eventL:Event;

 likeStyle=faLike;
 dislikeStyle=faDislike;
 like(){
  if(this.authService.logged){
    if(this.likeStyle == faLike)
      this.likeStyle = faLikeSelect;
    else
      this.likeStyle = faLike
  }else{
    this.router.navigate(['/login']);
  }
  
 }
 dislike(){
  if(this.authService.logged){
    if(this.dislikeStyle == faDislike)
      this.dislikeStyle = faDislikeSelect;
    else
      this.dislikeStyle = faDislike;
  }else{
    this.router.navigate(['/login']);
  }
 }
}
