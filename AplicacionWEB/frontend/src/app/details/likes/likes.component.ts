import { Component, Input, ElementRef, OnInit } from '@angular/core';
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
export class LikesComponent implements OnInit {
  constructor(private elementRef:ElementRef, private authService:AuthService, private router: Router, private eventService:EventService){}
  
  likeBarWidth: number = 0;
  dislikeBarWidth: number = 0;

  ngOnInit() {
    this.calculateBar();
  }

  @Input()
  eventL:Event;

 likeStyle=faLike;
 dislikeStyle=faDislike;
 like(){
  if(this.authService.logged){
    this.eventService.giveLike(this.eventL.id).subscribe(response=>{});
    if(this.likeStyle == faLike){
      this.likeStyle = faLikeSelect;
      this.eventL.totalLikes+=1;
    }
    else{
      this.likeStyle = faLike
      this.eventL.totalLikes-=1;
    }
    this.calculateBar();
    
  }else{
    this.router.navigate(['/login']);
  }
  
 }
 dislike(){
  if(this.authService.logged){
    this.eventService.giveDislike(this.eventL.id).subscribe(response=>{});
    if(this.dislikeStyle == faDislike){
      this.dislikeStyle = faDislikeSelect;
      this.eventL.totalDislikes+=1;
    }
      
    else{ 
      this.dislikeStyle = faDislike;
      this.eventL.totalDislikes-=1;
    }
    this.calculateBar();
    
  }else{
    this.router.navigate(['/login']);
  }
 }

 calculateBar() {
  const likes = this.eventL.totalLikes;
  const dislikes = this.eventL.totalDislikes;
  
  const total = likes + dislikes;

  const percentageLikes = (likes / total) * 100;
  const percentageDislikes = (dislikes / total) * 100;

  this.likeBarWidth = percentageLikes;
  this.dislikeBarWidth = percentageDislikes;

  if(total == 0){
    this.likeBarWidth = 50;
    this.dislikeBarWidth = 50;
  }

}
}
