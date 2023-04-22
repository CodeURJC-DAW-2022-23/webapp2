import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { Comment } from '../models/comment.model';

const BASE_URL = '/api/comments';

@Injectable({
  providedIn: 'root'
})
export class CommentService{

  constructor(private httpClient: HttpClient, private router: Router) { }


  getComment(id: number){
    return this.httpClient.get(BASE_URL + '/' + id).pipe() as Observable<Comment>;
}

deleteComment(id: number){
    return this.httpClient.delete(BASE_URL + '/' + id, { withCredentials: true });
}


}
