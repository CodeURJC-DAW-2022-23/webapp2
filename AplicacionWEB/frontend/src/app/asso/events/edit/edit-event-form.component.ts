import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as simpleDatatables from 'simple-datatables';
import { EventService } from 'src/app/services/event.service';
import { Event } from '../../../models/event.model';
@Component({
  selector: 'asso-edit-event',
  templateUrl: './edit-event-form.component.html',
  styleUrls: ['../../../../css/boostrap.css']
})

export class EditEventAssoComponent {

  event: Event;
  file = {} as File;
  areImage:boolean=false;
  campusValues = [
    {value: "ALCORCON", content: "Alcorcón", select: false},
    {value: "ARANJUEZ", content: "Aranjuez", select: false},
    {value: "FUENLABRADA", content: "Fuenlabrada", select: false},
    {value: "MOSTOLES", content: "Móstoles", select: false},
    {value: "MADRID-VICALVARO", content: "Madrid-Vicalvaro", select: false},
    {value: "MADRID-QUINTANA", content: "Madrid-Quintana", select: false}
  ]
  
  constructor(private eventService: EventService, private activatedRoute: ActivatedRoute, private router: Router){
    const idEvent = activatedRoute.snapshot.params['id'] as Number;
    this.eventService.eventById(idEvent).subscribe(
      eventIn=>this.event=eventIn
    )
  }
  ngOnInit(){
    this.areImage=false;
    
  }

  selected(i:boolean){
    if(i){
      return true;
    }else{
      return false;
    }
  }
  changeCredits(res:boolean){
    this.event.credits=res;
  }
  changeBooking(res:boolean){
    this.event.booking=res;
  }
  editEvent(e : any) {
    const [hours1, minutes1] = this.event.endTime.split(':');
    const [hours2, minutes2] = this.event.startTime.split(':');

    const formData = new FormData();
    formData.append('name', this.event.name);
    formData.append('date', this.event.date);
    formData.append('month', "agosto");
    formData.append('description', this.event.description);
    formData.append('location', this.event.location);
    formData.append('campus', this.event.campus);
    formData.append('credits', this.event?.credits?.toString() ?? 'false');
    formData.append('booking', this.event?.booking?.toString() ?? 'false');
    formData.append('startTime', this.event.startTime);
    formData.append('endTime', this.event.endTime);
    formData.append('duration', `${Number(hours1) - Number(hours2)}h ${Number(minutes1) - Number(minutes2)}min`);
    this.eventService.edit(formData, this.event.id).subscribe(response=>{
      
      if (this.areImage){
        const formDataImg=new FormData();
        formDataImg.append('newImage', this.file);
        
        this.eventService.sendImage(formDataImg,this.event.id).subscribe(response=>{});
      }
      this.router.navigate(['aso/eventManagerAso']);
    });
  }
  
  onFileSelected(e: any) {
    this.file = e.target.files[0];
    this.areImage=true;
    let fileInput = "";
    if (this.file)
      fileInput = this.file.name;  
  }
}