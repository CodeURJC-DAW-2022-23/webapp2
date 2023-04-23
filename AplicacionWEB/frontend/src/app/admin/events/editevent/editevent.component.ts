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

  editevent(id: number) {
    this.eventService.adminEditEvent(id, this.event).subscribe(response => {
       this.router.navigate(['admin/events']);
    });
  }
}
