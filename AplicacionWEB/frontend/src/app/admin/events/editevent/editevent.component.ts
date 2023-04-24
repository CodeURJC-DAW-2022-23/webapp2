import { Component } from '@angular/core';
import { Event } from 'src/app/models/event.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-editevent',
  templateUrl: './editevent.component.html',
  styleUrls: ['./editevent.component.css']
})
export class EditeventComponent {
  event: Event;

  constructor(private eventService: EventService){}

  ngOnInit(id: Number) {
    this.eventService.eventById(id).subscribe((response)=>{ 
      this.event = response;
    });
  }

  editevent(event : any) {
    event.preventDefault();
    this.eventService.adminEditEvent(this.event).subscribe(response=>{});
  }
}
