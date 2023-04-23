import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as simpleDatatables from 'simple-datatables';
import { EventService } from 'src/app/services/event.service';
import { Event } from '../../../models/event.model';
@Component({
  selector: 'asso-edit-event',
  templateUrl: './edit-event-form.component.html',
  styleUrls: ['../../../../css/boostrap.css', '../../../../css/boostrap.css']
})

export class editEventAssoComponent {

  event: Event;
  
  constructor(private eventService: EventService, private activatedRoute: ActivatedRoute){
    const idEvent = activatedRoute.snapshot.params['id'] as Number;
    this.eventService.eventById(idEvent).subscribe(
      eventIn=>this.event=eventIn
    )
  }


}