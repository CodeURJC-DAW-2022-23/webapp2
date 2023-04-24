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

  editEvent() {
    console.log(this.event.credits);
    console.log(this.event.booking);
    this.eventService.adminEditEvent(this.event.id, this.event).subscribe(response => {
       this.router.navigate(['admin/events']);
    });
  }
}
