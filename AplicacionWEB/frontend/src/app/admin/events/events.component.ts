import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from '../../models/event.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {
  id?: number;
  name?: string;
  date?: string;
  location?: string;
  ASOname?: string;
  event: Event|undefined;
  events:Event[];

  constructor(private router: Router, private eventService:EventService) { 
    this.eventService.allEvents().subscribe((response)=>{
      this.events=response;
    })
  }

  modify() {
     this.router.navigate(['/admin/editevent/', this.event?.id]);
  }

  delete(id: number) {
    this.eventService.deleteEvent(id);
  }

}
