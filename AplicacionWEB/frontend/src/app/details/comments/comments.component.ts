import { Component, OnInit } from '@angular/core';
import { Comment } from 'src/app/models/comment.model';
import { CommentService } from "src/app/services/comment.service";
import { Router, ActivatedRoute } from '@angular/router';

import * as $ from 'jquery';


@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {

 
  newComment!: boolean;
  comment!: Comment;

  totalLikes: number = 0;
  description = "Esto es un comentario"

  constructor(private service: CommentService) { }
  // ngOnInit(): void{

  //   const idComment = this.activatedRouter.snapshot.params['idComment'];

  //   if (idComment) {
  //       this.service.getComment(idComment).subscribe(
  //           response => {
  //               this.comment = response;
  //               this.totalLikes = this.comment.totalLikes;
  //               this.description = this.comment.description;
  //           },
  //           error => console.log(error)
  //       )

  //       this.newComment = false;
  //   } else {     
  //       this.newComment = true;
  //   }
  // }

  save() {
    if (this.newComment) {
       // this.filmService.addComment(this.id, this.totalLikes, this.description);
    }
  }
}
export { Comment };

