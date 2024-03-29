import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Event } from '../models/event.model';
import { Aso } from '../models/aso.model';

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
  giveLike(id:Number){
    const body={};
    return this.http.post(BASE_URL+'/like/'+id,body).pipe();
  }
  giveDislike(id:Number){
    const body={};
    return this.http.post(BASE_URL+'/dislike/'+id,body).pipe();
  }
  create(formData: FormData) {
    return this.http.post(BASE_URL + "/new", formData).pipe();
  }

  delete(id: Number) {
    return this.http.delete(BASE_URL + "/" + id).pipe();
  }

  edit(formData: FormData, id: Number) {
    return this.http.put(BASE_URL + "/" + id, formData).pipe();
  }
  
  sendImage(formData: FormData, id: Number) {
    console.log((formData.get("newImage")as File).size)
    return this.http.put(BASE_URL + "/image/" + id, formData).pipe();
  }
}
