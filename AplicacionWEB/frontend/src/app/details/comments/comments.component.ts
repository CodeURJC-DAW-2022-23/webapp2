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

 
  commentList:Comment[];
  comment: Comment;

  constructor(private service: CommentService) { }
 

  save() {
    
  }
}

