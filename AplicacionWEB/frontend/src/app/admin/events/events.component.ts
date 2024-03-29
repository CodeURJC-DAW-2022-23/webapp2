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
  events:Event[];

  constructor(private router: Router, private eventService:EventService) { 
    
  }
  ngOnInit(){
    this.eventService.allEvents().subscribe((response)=>{
      this.events=response;
    })
  }
  
  modify(id: number) {
     this.router.navigate(['/admin/editevent/', id]);
  }

  deleteEvent(id: number) {
    this.eventService.delete(id).subscribe(
      response =>{
        this.ngOnInit();
      });
  }

}
