import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  allEvents():Observable<any>{
    return this.http.get(BASE_URL+"/all").pipe() as Observable<Event[]>;
  }
  eventById(id:Number):Observable<Event>{
    return this.http.get(BASE_URL+"/"+id).pipe() as Observable<Event>;
  }

  deleteEvent(id: number) {
    return this.http.delete(BASE_URL + '/' + id, { withCredentials: true });
  }

   adminEditEvent(event: Event) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.put(BASE_URL + '/editEvent', JSON.stringify(event),{headers}).pipe();
  }
}
