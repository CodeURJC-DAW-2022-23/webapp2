import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';


@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {

  totalLikes = "2"
  description = "Esto es un comentario"
  constructor() { }
  ngOnInit() {
    $("#addComment").on("click", function () {
      
    });
  }
}
