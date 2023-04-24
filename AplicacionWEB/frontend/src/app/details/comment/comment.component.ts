import { Component, Input } from '@angular/core';
import { Comment } from 'src/app/models/comment.model';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { faHeart as faLikeSelect} from '@fortawesome/free-solid-svg-icons';
import { faHeart as faLike} from '@fortawesome/free-regular-svg-icons';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {
  @Input()
  comment:Comment;
  constructor(private authService:AuthService, private router: Router,private commentService:CommentService){}
  heart = faLike;

  like(){
    if(this.authService.logged){
      
      this.commentService.giveLike(this.comment.id).subscribe(response=>{});
      if(this.heart == faLike){
        this.heart = faLikeSelect;
        this.comment.totalLikes+=1;
      }
      else{
        this.heart = faLike
        this.comment.totalLikes-=1;
      }
    }else{
      this.router.navigate(['/login']);
    }
  }

  
}
