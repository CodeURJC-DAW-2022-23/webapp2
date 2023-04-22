import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from '../../models/event.model';

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

  constructor(private router: Router) { }

  modify() {
     this.router.navigate(['/admin/editevent/', this.event?.id]);
  }

  ngOnInit() {
    this.loadEvent();
  }

  loadEvent() {
     this.eventService.getEvent().subscribe(
      response=>{
        this.e = response;
        this.name=this.e.name;
      }
    );
  }
}
