import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'asso-events',
  templateUrl: './events.component.html',
  styleUrls: ['../../../css/boostrap.css', './events.component.css']
})

export class Events {
  events: Event[];

  constructor(private assoService: AsoService){}

  ngOnInit() {
    this.assoService.getEvents().subscribe((response)=>{
      this.events = response;
    });
  }
}