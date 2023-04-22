import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { EventService } from '../services/event.service';
import { Event } from '../models/event.model';

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.css','../../css/boostrap.css']
})
export class EventInfoComponent {
  event:Event;
  constructor(private router: RouterModule, activatedRoute: ActivatedRoute, private eventService:EventService){
    const idEvent = activatedRoute.snapshot.params['id'] as Number;
    this.eventService.eventById(idEvent).subscribe(
      eventIn=>this.event=eventIn
    )
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
