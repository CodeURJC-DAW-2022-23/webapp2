import { Component, OnInit } from '@angular/core';
import { Comment } from 'src/app/models/comment.model';
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

  constructor() { }
  ngOnInit() {
    $("#addComment").on("click", function () {
      
    });
  }
}
