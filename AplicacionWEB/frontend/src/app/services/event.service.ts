import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Event } from '../models/event.model';

const BASE_URL = '/api/events';
@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  eventsByFilters(search:String, aso:String, month:String, campus:String, page:Number):Observable<any>{
    return this.http.get(BASE_URL+"/filters?name="+search+"&month="+month+"&campus="+campus+"&asociation="+aso+"&page="+page).pipe() as Observable<Event[]>;
  }
}
