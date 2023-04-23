import { Component, OnInit, Optional } from '@angular/core';
import { Event } from 'src/app/models/event.model';
import * as simpleDatatables from 'simple-datatables';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'asso-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['../asso.component.css', '../../../css/boostrap.css']
})

export class AssoNewEvent {
  event = {} as Event;
  file = {} as File;

  constructor(private eventService: EventService){}

  createEvent(e : any) {
    const [hours1, minutes1] = this.event.endTime.split(':');
    const [hours2, minutes2] = this.event.startTime.split(':');

    const formData = new FormData();
    if (this.file){
      formData.append('newImage', this.file);
    }
    formData.append('name', this.event.name);
    formData.append('date', this.event.date);
    formData.append('month', "agosto");
    formData.append('description', this.event.description);
    formData.append('location', this.event.location);
    formData.append('campus', this.event.campus);
    formData.append('credits', this.event?.credits?.toString() ?? 'false');
    formData.append('booking', this.event?.booking?.toString() ?? 'false');
    formData.append('starTime', this.event.startTime);
    formData.append('endTime', this.event.endTime);
    formData.append('duration', `${Number(hours1) - Number(hours2)}h ${Number(minutes1) - Number(minutes2)}min`);
    this.eventService.create(formData).subscribe(response=>{});
  }

  onFileSelected(e: any) {
    this.file = e.target.files[0];
    let fileInput = "";
    if (this.file)
      fileInput = this.file.name;  
  }
}
