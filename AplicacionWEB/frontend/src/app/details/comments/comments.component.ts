import { Component, Input, OnInit } from '@angular/core';
import { Comment } from 'src/app/models/comment.model';
import { CommentService } from "src/app/services/comment.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Event } from 'src/app/models/event.model';
import * as $ from 'jquery';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {

  @Input()
  eventC:Event;
  commentList:Comment[];
  comment: Comment = {} as Comment;
  description:string;

  constructor(private commentService: CommentService,private authService:AuthService, private router: Router) { }
  ngOnInit(){
    this.commentService.commentList(this.eventC.id).subscribe(
      comments=>this.commentList=comments
    )
  }

  save() {
    if(this.authService.logged){
      this.comment.event=this.eventC;
      this.comment.description=this.description;
      this.commentService.addComment(this.comment).subscribe(response=>{});
      this.description="";
    }else{
      this.router.navigate(['/login']);
    }
  }
}

