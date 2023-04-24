import { Component } from '@angular/core';
import { Event } from 'src/app/models/event.model';
import { EventService } from 'src/app/services/event.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-editevent',
  templateUrl: './editevent.component.html',
  styleUrls: ['./editevent.component.css']
})
export class EditeventComponent {
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
  constructor(private router: Router, activatedRoute: ActivatedRoute, private eventService: EventService) {
    const idEvent = activatedRoute.snapshot.params['id'] as number;
    this.load(idEvent);
  }

  load(id: number){
    this.eventService.eventById(id).subscribe((response)=>{ 
      this.event = response;
    });
  }
  ngOnInit() {
    
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
      this.router.navigate(['admin/events']);
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
