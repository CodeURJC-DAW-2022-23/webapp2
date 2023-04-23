import { Component, Input } from '@angular/core';
import { Comment } from 'src/app/models/comment.model';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { faHeart as faLikeSelect} from '@fortawesome/free-solid-svg-icons';
import { faHeart as faLike} from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {
  constructor(private authService:AuthService, private router: Router){}
  heart = faLike;

  like(){
    if(this.authService.logged){
      if(this.heart == faLike)
        this.heart = faLikeSelect;
      else
        this.heart = faLike
    }else{
      this.router.navigate(['/login']);
    }
  }

  @Input()
  comment:Comment;
}
