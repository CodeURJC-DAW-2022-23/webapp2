import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';
import { Event } from 'src/app/models/event.model';
import { Router } from '@angular/router';

@Component({
  selector: 'asso-events',
  templateUrl: './events.component.html',
  styleUrls: ['../../../css/boostrap.css', './events.component.css']
})

export class Events {
deleteEvent(arg0: string) {
throw new Error('Method not implemented.');
}
goToPage(pageName: string) {
  this.router.navigate([`${pageName}`]);
}
  events: Event[];

  constructor(private assoService: AsoService, private router: Router){}

  ngOnInit() {
    this.assoService.getEvents().subscribe((response)=>{
      this.events = response;
    });
  }
}