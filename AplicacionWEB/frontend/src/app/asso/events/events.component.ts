import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';
import { Event } from 'src/app/models/event.model';
import { Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'asso-events',
  templateUrl: './events.component.html',
  styleUrls: ['../../../css/boostrap.css', './events.component.css']
})

export class Events {

  constructor(private eventService: EventService, private assoService: AsoService, private router: Router){}

  deleteEvent(id : Number) {
    this.eventService.delete(id).subscribe((response)=>{});
  }

  goToPage(pageName: string) {
    this.router.navigate([`${pageName}`]);
  }
    events: Event[];


  ngOnInit() {
    this.assoService.getEvents().subscribe((response)=>{
      this.events = response;
    });
  }
}
