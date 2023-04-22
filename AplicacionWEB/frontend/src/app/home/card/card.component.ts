import { Component, Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input()
  event:Event;
  constructor(private router: Router, activatedRoute:ActivatedRoute){}

  
  infoEvent(){
    this.router.navigate(['/infoEvento/',this.event?.id]);
  }
imageURL(){
    return '/api/events/image/'+this.event.id;
}
getBooking(){
    if (this.event.booking){
      return 'Si';
    }else{
      return 'No';
    }
}
getCredits(){
    if (this.event.credits){
      return 'Si';
    }else{
      return 'No';
    }
}
}
