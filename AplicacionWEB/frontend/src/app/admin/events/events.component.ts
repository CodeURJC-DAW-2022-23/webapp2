import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Event } from '../../models/event.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
  
export class EventsComponent {
  e?: Event;
  id?: number;
  name?: String;
  date?: string;
  location?: string;
  ASOname?: string;
  event: Event|undefined;

  constructor(private eventService: EventService, private authService:AuthService,private router: Router){
    
  }

  modify() {
     this.router.navigate(['/admin/editevent/', this.event?.id]);
  }

  ngOnInit() {
    this.loadEvent();
  }

  loadEvent() {
     this.eventService.getEvent().subscribe(
      response=>{
        this.e = response;
        this.name = this.e.name;
      }
    );
  }

  logout(){
    this.authService.logOut();
    this.router.navigate(['']);
  }
}
