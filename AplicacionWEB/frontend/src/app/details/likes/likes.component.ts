import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/app/models/event.model';
import { AuthService } from 'src/app/services/auth.service';
import { faFaceLaugh as faLikeSelect, faFaceFrownOpen as faDislikeSelect} from '@fortawesome/free-solid-svg-icons';
import { faFaceLaugh as faLike, faFaceFrownOpen as faDislike} from '@fortawesome/free-regular-svg-icons';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-likes',
  templateUrl: './likes.component.html',
  styleUrls: ['./likes.component.css']
})
export class LikesComponent {
  constructor(private authService:AuthService, private router: Router, private eventService:EventService){}
 
  @Input()
  eventL:Event;

 likeStyle=faLike;
 dislikeStyle=faDislike;
 like(){
  if(this.authService.logged){
    this.eventService.giveLike(this.eventL.id).subscribe(response=>{});
    this.eventL.totalLikes+=1;
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
    this.eventService.giveDislike(this.eventL.id).subscribe(response=>{});
    this.eventL.totalDislikes+=1;
    if(this.dislikeStyle == faDislike)
      this.dislikeStyle = faDislikeSelect;
    else
      this.dislikeStyle = faDislike;
  }else{
    this.router.navigate(['/login']);
  }
 }
}
