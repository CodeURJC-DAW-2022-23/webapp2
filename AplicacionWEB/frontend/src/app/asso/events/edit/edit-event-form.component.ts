import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as simpleDatatables from 'simple-datatables';
import { EventService } from 'src/app/services/event.service';
import { Event } from '../../../models/event.model';
@Component({
  selector: 'asso-edit-event',
  templateUrl: './edit-event-form.component.html',
  styleUrls: ['../../../../css/boostrap.css', '../../../../css/boostrap.css']
})

export class editEventAssoComponent {

  e: Event;

  constructor(private eventService: EventService){}


}