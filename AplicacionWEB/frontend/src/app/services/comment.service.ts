import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { Comment } from '../models/comment.model';

const BASE_URL = '/api/comments';

@Injectable({
  providedIn: 'root'
})
export class CommentService{

  constructor(private httpClient: HttpClient, private router: Router) { }

  getComment(id: number){
    return this.httpClient.get(BASE_URL + '/' + id).pipe(
    ) as Observable<Comment>;
}

deleteComment(id: number){
    return this.httpClient.delete(BASE_URL + '/' + id, { withCredentials: true });
}



private handleError(error: any) {
console.log("ERROR:");
console.error(error);
return throwError("Server error (" + error.status + "): " + error.text())
}
}
