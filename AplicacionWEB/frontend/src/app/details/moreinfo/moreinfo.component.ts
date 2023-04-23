import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginComponent } from 'src/app/login/login.component';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-moreinfo',
  templateUrl: './moreinfo.component.html',
  styleUrls: ['./moreinfo.component.css']
})
export class MoreinfoComponent {

  event:Event;
  constructor(private router: RouterModule, activatedRoute: ActivatedRoute, private eventService:EventService,private http: HttpClient){
    const idEvent = activatedRoute.snapshot.params['id'] as Number;
    this.eventService.eventById(idEvent).subscribe(
      eventIn=>this.event=eventIn
    )
  }
  login = "http://localhost:4200/login";
  getLogin(){
    return this.http.get<LoginComponent>(this.login)
  }
  imageURL(){
    return '/api/events/image/'+this.event.id;
  }

getBooking(){
    if (this.event.booking){
      return 'Si';
    }else{
      return 'No';
    }
}
getCredits(){
    if (this.event.credits){
      return 'Si';
    }else{
      return 'No';
    }
}
}
